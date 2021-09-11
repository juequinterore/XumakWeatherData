package co.me.infrastructure.city.repository

import co.me.domain.city.ICityRepository
import co.me.domain.entities.City
import co.me.domain.value_objects.WeatherDay
import co.me.domain.value_objects.WeekDay
import co.me.infrastructure.city.repository.data_sources.ICityLocalDataSource
import co.me.infrastructure.city.repository.data_sources.ICityRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CityRepository(
    private val remoteCityDataSource: ICityRemoteDataSource,
    private val localCityDataSource: ICityLocalDataSource
) : ICityRepository {
    override fun getAllCities(): Flow<List<City>> =
        localCityDataSource.getAllCities().map { allCityDts -> allCityDts.map { it.toDomain() } }

    override suspend fun searchCityByName(name: String): City? =
        remoteCityDataSource.searchCityByName(name)?.toDomain()

    override suspend fun getCityWeather(cityId: Int): Map<WeekDay, WeatherDay> =
        remoteCityDataSource.getCityWeather(cityId).entries
            .map { entry -> WeekDay(entry.key) to entry.value.toDomain() }
            .toMap()
}