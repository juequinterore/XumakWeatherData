package co.me.xumakweathedata.infrastructure.city.repository

import co.me.xumakweathedata.infrastructure.city.dtos.CityDto
import co.me.xumakweathedata.infrastructure.city.dtos.WeatherDayDto
import co.me.xumakweathedata.infrastructure.city.repository.data_sources.ICityRemoteDataSource
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
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

class CityRepositoryTest {

    @Test
    fun `should call remoteDataSource with received name`() = runBlocking {
        //Arrange
        val mockCityRemoteDataSource = FakeCityRemoteDataSource()
        val cityRepository = CityRepository(remoteRemoteDataSource = mockCityRemoteDataSource)

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
        val mockCityRemoteDataSource = FakeCityRemoteDataSource(fakeCityDto)
        val cityRepository = CityRepository(remoteRemoteDataSource = mockCityRemoteDataSource)

        //Act
        val city = cityRepository.searchCityByName("Medellín")

        //Assert
        assertEquals(city, fakeCityDto.toDomain())
    }

}