package co.me.domain.city.use_cases

import co.me.domain.city.ICityRepository
import co.me.domain.entities.City
import co.me.domain.value_objects.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test


class SearchCityByNameTest {

    private class FakeCityRepository(
        private val city: City? = null,
        private val weather: Map<WeekDay, WeatherDay> = mapOf()
    ) : ICityRepository {

        private var _cityName: String? = null

        val cityName: String?
            get() = _cityName


        override fun getAllCities(): Flow<List<City>> {
            TODO("Not yet implemented")
        }

        override suspend fun insert(city: City) {
            TODO("Not yet implemented")
        }

        override suspend fun searchCityByName(name: String): City? {
            _cityName = name
            return city
        }

        override suspend fun getCityWeather(cityId: Int): Map<WeekDay, WeatherDay> {
            return if (cityId == city?.id)
                weather
            else
                mapOf()
        }
    }

    @Test
    fun `should call repository searchCityByName with received name`() = runBlocking {
        //Arrange
        val mockCityRepository = FakeCityRepository()
        val getCityWeather = GetCityWeather(mockCityRepository)
        val searchCityByName = SearchCityByName(mockCityRepository, getCityWeather)

        //Act
        searchCityByName(SearchCityByNameCommand("Medel"))

        //Assert
        assertEquals(mockCityRepository.cityName, "Medel")
    }

    @Test
    fun `should return city from received CityRepository if city exists`() = runBlocking {
        //Arrange
        val city = City(
            id = 111,
            name = "Medellín",
            imageUrl = XUrl("https://medellin.gov.co/image.png"),
            weather = emptyMap()
        )

        val mockCityRepository = FakeCityRepository(city = city, weather = weather)
        val getCityWeather = GetCityWeather(mockCityRepository)
        val searchCityByName = SearchCityByName(mockCityRepository, getCityWeather)

        //Act
        val cityAnswer = searchCityByName(SearchCityByNameCommand("Medel"))

        //Assert
        assertEquals(cityAnswer, city)

    }

    @Test
    fun `should return null city from received CityRepository if city does not exist`() =
        runBlocking {
            //Arrange
            val mockCityRepository = FakeCityRepository(city = null)
            val getCityWeather = GetCityWeather(mockCityRepository)
            val searchCityByName = SearchCityByName(mockCityRepository, getCityWeather)

            //Act
            val cityAnswer = searchCityByName(SearchCityByNameCommand("Mace"))

            //Assert
            assertNull(cityAnswer)

        }

}