package co.me.infrastructure.city.repository

import co.me.domain.city.ICityRepository
import co.me.domain.entities.City
import co.me.domain.value_objects.WeatherDay
import co.me.domain.value_objects.WeekDay
import co.me.infrastructure.city.dtos.CityDto
import co.me.infrastructure.city.repository.data_sources.ICityRemoteDataSource
import co.me.infrastructure.city.repository.data_sources.local.ICityLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CityRepository(
    private val remoteCityDataSource: ICityRemoteDataSource,
    private val localCityDataSource: ICityLocalDataSource
) : ICityRepository {
    override fun getAllCities(): Flow<List<City>> =
        localCityDataSource.getAllCities()
            .map { allCityDts ->
                allCityDts.map { it.toDomain() }
            }

    override suspend fun insert(city: City) =
        localCityDataSource.insert(CityDto.fromDomain(city))


    override suspend fun searchCityByName(name: String): City? =
        remoteCityDataSource.searchCityByName(name)?.toDomain()

    override suspend fun searchCitiesByName(name: String): List<City> =
        remoteCityDataSource.searchCitiesByName(name).map { it.toDomain() }

    override suspend fun getCityWeather(cityId: Int): Map<WeekDay, WeatherDay> =
        remoteCityDataSource.getCityWeather(cityId).entries
            .map { entry -> WeekDay(entry.key) to entry.value.toDomain() }
            .toMap()

    override suspend fun deleteCity(city: City) = localCityDataSource.delete(CityDto.fromDomain(city))
}