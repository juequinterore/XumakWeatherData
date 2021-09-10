package co.me.domain.city.use_cases

import co.me.domain.city.ICityRepository
import co.me.domain.entities.City
import co.me.domain.value_objects.XUrl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class GetTopCitiesTest {

    private class FakeCityRepository(private val topCities: List<City>) : ICityRepository {

        override fun getTopCities(): Flow<List<City>> = flow { emit(topCities) }

        override fun searchCityByName(name: String): City? {
            TODO("Not yet implemented")
        }

    }

    @Test
    fun `should return top cities from received CityRepository`() = runBlocking {
        //Arrange
        val cities = listOf(
            City(
                id = 111,
                name = "Medellín",
                imageUrl = XUrl("https://medellin.gov.co/image.png"),
                isTopCity = true,
                weather = emptyMap()
            ),
            City(
                id = 112,
                name = "Brisbane",
                imageUrl = XUrl("https://brisbane.gov.co/image.png"),
                isTopCity = true,
                weather = emptyMap()
            )
        )

        val mockCityRepository = FakeCityRepository(topCities = cities)
        val getTopCitiesUseCase = GetTopCities(mockCityRepository)

        //Act
        val topCities = getTopCitiesUseCase(Unit).first()

        //Assert
        assertEquals(topCities, cities)
    }

}