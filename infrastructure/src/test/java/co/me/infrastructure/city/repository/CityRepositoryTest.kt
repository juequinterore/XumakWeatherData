package co.me.infrastructure.city.repository

import co.me.infrastructure.city.dtos.CityDto
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

class FakeCityRemoteDataSource(private val searchCity: CityDto? = null) : ICityRemoteDataSource {

    private var _searchCityName: String? = null

    val searchCityName
        get() = _searchCityName

    override fun searchCityByName(cityName: String): CityDto? {
        _searchCityName = cityName

        return searchCity
    }

    override fun getCityWeather(cityId: Int): Map<Int, WeatherDayDto> {
        TODO("Not yet implemented")
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
        allCities: List<CityDto> = emptyList()
    ): CityRepository {

        val mockCityRemoteDataSource = FakeCityRemoteDataSource(searchCity = searchCity)
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