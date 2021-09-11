package co.me.infrastructure.city.repository

import co.me.domain.value_objects.*
import co.me.infrastructure.city.dtos.CityDto
import co.me.infrastructure.city.dtos.HourlyWeatherDto
import co.me.infrastructure.city.dtos.WeatherDayDto
import co.me.infrastructure.city.repository.data_sources.ICityLocalDataSource
import co.me.infrastructure.city.repository.data_sources.ICityRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class FakeCityRemoteDataSource(
    private val searchCity: CityDto? = null,
    private val cityWeather: Map<Int, WeatherDayDto> = mapOf()
) : ICityRemoteDataSource {

    private var _searchCityName: String? = null

    val searchCityName
        get() = _searchCityName

    private var _getCityWeatherCityId: Int? = null

    val getCityWeatherCityId
        get() = _getCityWeatherCityId

    override fun searchCityByName(cityName: String): CityDto? {
        _searchCityName = cityName

        return searchCity
    }

    override fun getCityWeather(cityId: Int): Map<Int, WeatherDayDto> {
        _getCityWeatherCityId = cityId
        return cityWeather
    }

}

class FakeCityLocalDataSource(private val allCities: List<CityDto> = emptyList()) :
    ICityLocalDataSource {

    private var _isGetAllCitiesCalled = false

    val isGetAllCitiesCalled
        get() = _isGetAllCitiesCalled

    override fun getAllCities(): Flow<List<CityDto>> {
        _isGetAllCitiesCalled = true
        return flow { emit(allCities) }
    }

}

class CityRepositoryTest {

    private fun createCityRepository(
        searchCity: CityDto? = null,
        weatherDays: Map<Int, WeatherDayDto> = mapOf(),
        allCities: List<CityDto> = emptyList()
    ): CityRepository {

        val mockCityRemoteDataSource =
            FakeCityRemoteDataSource(searchCity = searchCity, cityWeather = weatherDays)
        val fakeCityLocalDataSource = FakeCityLocalDataSource(allCities = allCities)
        return CityRepository(
            remoteCityDataSource = mockCityRemoteDataSource,
            localCityDataSource = fakeCityLocalDataSource
        )

    }

    @Test
    fun `should call remoteDataSource with received name`() = runBlocking {
        //Arrange
        val mockCityRemoteDataSource = FakeCityRemoteDataSource()
        val fakeCityLocalDataSource = FakeCityLocalDataSource()
        val cityRepository = CityRepository(
            remoteCityDataSource = mockCityRemoteDataSource,
            localCityDataSource = fakeCityLocalDataSource
        )

        //Act
        cityRepository.searchCityByName("Medellín")

        //Assert
        assertEquals(mockCityRemoteDataSource.searchCityName, "Medellín")
    }

    @Test
    fun `should return remoteDataSource CityDto converted into City`() = runBlocking {
        //Arrange
        val fakeCityDto = CityDto(
            geonameid = 20001201,
            name = "Medellín",
            imageUrl = "https://myImage.co/image.jpg",
            weather = mapOf()
        )
        val cityRepository = createCityRepository(searchCity = fakeCityDto)

        //Act
        val city = cityRepository.searchCityByName("Medellín")

        //Assert
        assertEquals(city, fakeCityDto.toDomain())
    }

    @Test
    fun `should call remoteDataSource to get city weather with received cityId`() = runBlocking {
        //Arrange
        val mockCityRemoteDataSource = FakeCityRemoteDataSource()
        val fakeCityLocalDataSource = FakeCityLocalDataSource()
        val cityRepository = CityRepository(
            remoteCityDataSource = mockCityRemoteDataSource,
            localCityDataSource = fakeCityLocalDataSource
        )

        //Act
        cityRepository.getCityWeather(123)

        //Assert
        assertEquals(123, mockCityRemoteDataSource.getCityWeatherCityId)

    }

    @Test
    fun `should return remoteDataSource Map-Int, WeatherDayDto- converted into Map-Int, WeatherDay-`() =
        runBlocking {
            //Arrange
            val weatherDaysDtos = mapOf(
                0 to WeatherDayDto(
                    dayOfTheWeek = 0,
                    weatherType = "sunny",
                    low = 87,
                    high = 110,
                    hourlyWeather = mapOf(
                        2 to HourlyWeatherDto(
                            weatherType = "sunny",
                            rainChance = 0.0,
                            temperature = 94,
                            hour = 2,
                            humidity = 0.1,
                            windSpeed = 6.0
                        ),
                        14 to HourlyWeatherDto(
                            weatherType = "sunny",
                            rainChance = 0.0,
                            temperature = 108,
                            hour = 14,
                            humidity = 0.1,
                            windSpeed = 6.0
                        ),
                        15 to HourlyWeatherDto(
                            weatherType = "sunny",
                            rainChance = 0.0,
                            temperature = 109,
                            hour = 15,
                            humidity = 0.1,
                            windSpeed = 6.0
                        )
                    )
                ),
                4 to WeatherDayDto(
                    dayOfTheWeek = 4,
                    weatherType = "sunny",
                    low = 96,
                    high = 120,
                    hourlyWeather = mapOf(
                        0 to HourlyWeatherDto(
                            weatherType = "sunny",
                            rainChance = 0.0,
                            temperature = 104,
                            hour = 0,
                            humidity = 0.1,
                            windSpeed = 6.0
                        ),
                        7 to HourlyWeatherDto(
                            weatherType = "sunny",
                            rainChance = 0.0,
                            temperature = 98,
                            hour = 7,
                            humidity = 0.1,
                            windSpeed = 6.0
                        ),
                        23 to HourlyWeatherDto(
                            weatherType = "sunny",
                            rainChance = 0.0,
                            temperature = 109,
                            hour = 23,
                            humidity = 0.1,
                            windSpeed = 6.0
                        )
                    )
                )
            )
            val cityRepository = createCityRepository(weatherDays = weatherDaysDtos)

            //Act
            val city = cityRepository.getCityWeather(1234)

            //Assert
            val expected = mapOf(
                WeekDay(0) to WeatherDay(
                    dayOfTheWeek = WeekDay(0),
                    weatherType = WeatherType.SUNNY,
                    low = 87,
                    high = 110,
                    hourlyWeather = mapOf(
                        DayHour(2) to HourlyWeather(
                            weatherType = WeatherType.SUNNY,
                            rainChance = Probability(0.0),
                            temperature = 94,
                            hour = DayHour(2),
                            humidity = Probability(0.1),
                            windSpeed = 6.0
                        ),
                        DayHour(14) to HourlyWeather(
                            weatherType = WeatherType.SUNNY,
                            rainChance = Probability(0.0),
                            temperature = 108,
                            hour = DayHour(14),
                            humidity = Probability(0.1),
                            windSpeed = 6.0
                        ),
                        DayHour(15) to HourlyWeather(
                            weatherType = WeatherType.SUNNY,
                            rainChance = Probability(0.0),
                            temperature = 109,
                            hour = DayHour(15),
                            humidity = Probability(0.1),
                            windSpeed = 6.0
                        )
                    )
                ),
                WeekDay(4) to WeatherDay(
                    dayOfTheWeek = WeekDay(4),
                    weatherType = WeatherType.SUNNY,
                    low = 96,
                    high = 120,
                    hourlyWeather = mapOf(
                        DayHour(0) to HourlyWeather(
                            weatherType = WeatherType.SUNNY,
                            rainChance = Probability(0.0),
                            temperature = 104,
                            hour = DayHour(0),
                            humidity = Probability(0.1),
                            windSpeed = 6.0
                        ),
                        DayHour(7) to HourlyWeather(
                            weatherType = WeatherType.SUNNY,
                            rainChance = Probability(0.0),
                            temperature = 98,
                            hour = DayHour(7),
                            humidity = Probability(0.1),
                            windSpeed = 6.0
                        ),
                        DayHour(23) to HourlyWeather(
                            weatherType = WeatherType.SUNNY,
                            rainChance = Probability(0.0),
                            temperature = 109,
                            hour = DayHour(23),
                            humidity = Probability(0.1),
                            windSpeed = 6.0
                        )
                    )
                )
            )
            assertEquals(city, expected)
        }

    @Test
    fun `should call localDataSource to get all cities`() = runBlocking {
        //Arrange
        val fakeCityRemoteDataSource = FakeCityRemoteDataSource()
        val mockCityLocalDataSource = FakeCityLocalDataSource()
        val cityRepository = CityRepository(
            remoteCityDataSource = fakeCityRemoteDataSource,
            localCityDataSource = mockCityLocalDataSource
        )

        //Act
        cityRepository.getAllCities()

        //Assert
        assertTrue(mockCityLocalDataSource.isGetAllCitiesCalled)

    }

    @Test
    fun `should return localDataSource CityDtos converted into Cities`() = runBlocking {
        //Arrange
        val fakeCityDto = CityDto(
            geonameid = 20001201,
            name = "Medellín",
            imageUrl = "https://myImage.co/image.jpg",
            weather = mapOf()
        )
        val fakeCityDto2 = CityDto(
            geonameid = 2111221,
            name = "Bogotá",
            imageUrl = "https://myImage.co/image2.jpg",
            weather = mapOf()
        )
        val cityRepository = createCityRepository(allCities = listOf(fakeCityDto, fakeCityDto2))

        //Act
        val cities = cityRepository.getAllCities().first()

        //Assert
        assertEquals(cities, listOf(fakeCityDto.toDomain(), fakeCityDto2.toDomain()))
    }


}