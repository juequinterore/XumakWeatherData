package co.me.infrastructure.city.repository.data_sources.local

import co.me.infrastructure.city.dtos.CityDto
import kotlinx.coroutines.flow.Flow

interface ICityLocalDataSource {
    fun insert(citiDto: CityDto)
    fun getAllCities(): Flow<List<CityDto>>
}