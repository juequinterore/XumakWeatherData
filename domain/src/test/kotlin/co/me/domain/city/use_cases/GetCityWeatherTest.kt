package co.me.domain.city.use_cases

import co.me.domain.city.ICityRepository
import co.me.domain.entities.City
import co.me.domain.value_objects.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class GetCityWeatherTest {

    private class FakeCityRepository(private val cityWeather: Map<WeekDay, WeatherDay> = mapOf()) :
        ICityRepository {

        private var _getCityWeatherCityId: Int? = null
        val getCityWeatherCityId
            get() = _getCityWeatherCityId

        override fun getAllCities(): Flow<List<City>> {
            TODO("Not yet implemented")
        }

        override suspend fun insert(city: City) {
            TODO("Not yet implemented")
        }

        override suspend fun searchCityByName(name: String): City? {
            TODO("Not yet implemented")
        }

        override suspend fun getCityWeather(cityId: Int): Map<WeekDay, WeatherDay> {
            _getCityWeatherCityId = cityId
            return cityWeather
        }

    }

    @Test
    fun `should call CityRepository with received parameter`() = runBlocking {

        //Arrange
        val mockRepository = FakeCityRepository()
        val getCityWeather = GetCityWeather(mockRepository)

        //Act
        getCityWeather(GetCityWeatherCommand(123456))

        //Assert
        assertEquals(mockRepository.getCityWeatherCityId, 123456)

    }

    @Test
    fun `should return city weather from CityRepository`() = runBlocking {
        //Arrange
        val cityWeather = mapOf(
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
        val cityRepository = FakeCityRepository(cityWeather)
        val getCityWeather = GetCityWeather(cityRepository)

        //Act
        val cityWeatherResponse = getCityWeather(GetCityWeatherCommand(1234098))

        //Assert
        assertEquals(cityWeatherResponse, cityWeather)
    }

}