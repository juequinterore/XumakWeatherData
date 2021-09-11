package co.me.xumakweathedata.infrastructure.city.repository

import co.me.domain.city.ICityRepository
import co.me.domain.entities.City
import co.me.xumakweathedata.infrastructure.city.repository.data_sources.ICityRemoteDataSource
import kotlinx.coroutines.flow.Flow

class CityRepository(private val remoteRemoteDataSource: ICityRemoteDataSource) : ICityRepository {
    override fun getTopCities(): Flow<List<City>> {
        TODO("Not yet implemented")
    }

    override suspend fun searchCityByName(name: String): City? =
        remoteRemoteDataSource.searchCityByName(name)?.toDomain()
}