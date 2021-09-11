package co.me.domain.city.use_cases

import co.me.domain.city.ICityRepository
import co.me.domain.entities.City
import co.me.domain.value_objects.XUrl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test


class SearchCityByNameTest {

    private class FakeCityRepository(private val city: City?) : ICityRepository {

        override fun getTopCities(): Flow<List<City>> {
            TODO("Not yet implemented")
        }

        override suspend fun searchCityByName(name: String): City? = city

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

        val mockCityRepository = FakeCityRepository(city = city)
        val searchCityByName = SearchCityByName(mockCityRepository)

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
            val searchCityByName = SearchCityByName(mockCityRepository)

            //Act
            val cityAnswer = searchCityByName(SearchCityByNameCommand("Mace"))

            //Assert
            assertNull(cityAnswer)

        }

}