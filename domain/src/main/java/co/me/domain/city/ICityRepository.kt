package co.me.domain.city

import co.me.domain.entities.City
import co.me.domain.value_objects.WeatherDay
import co.me.domain.value_objects.WeekDay
import kotlinx.coroutines.flow.Flow

interface ICityRepository {

    fun getAllCities(): Flow<List<City>>

    suspend fun insert(city: City)

    suspend fun searchCityByName(name: String): City?

    suspend fun searchCitiesByName(name: String): List<City>

    suspend fun getCityWeather(cityId: Int): Map<WeekDay, WeatherDay>

}