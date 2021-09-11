package co.me.domain.city.use_cases

import co.me.domain.city.ICityRepository
import co.me.domain.entities.City
import co.me.domain.use_cases.IQueryUseCase
import kotlinx.coroutines.flow.Flow

interface IGetAllCities : IQueryUseCase<Unit, Flow<List<City>>>

class GetAllCities(private val cityRepository: ICityRepository) : IGetAllCities {

    override suspend fun invoke(command: Unit): Flow<List<City>> = cityRepository.getAllCities()

}