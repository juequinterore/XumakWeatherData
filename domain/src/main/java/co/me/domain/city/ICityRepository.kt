package co.me.domain.city

import co.me.domain.entities.City
import kotlinx.coroutines.flow.Flow

interface ICityRepository {

    fun getTopCities(): Flow<List<City>>

    suspend fun searchCityByName(name: String): City?

}