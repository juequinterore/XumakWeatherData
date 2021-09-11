package co.me.infrastructure.city.repository.data_sources

import co.me.infrastructure.city.dtos.CityDto
import kotlinx.coroutines.flow.Flow

interface ICityLocalDataSource {
    fun getAllCities(): Flow<List<CityDto>>
}