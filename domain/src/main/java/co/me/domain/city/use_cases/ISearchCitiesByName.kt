package co.me.domain.city.use_cases

import co.me.domain.city.ICityRepository
import co.me.domain.entities.City
import co.me.domain.use_cases.IQueryUseCase

data class SearchCitiesByNameCommand(val name: String)

interface ISearchCitiesByName : IQueryUseCase<SearchCitiesByNameCommand, List<City>>

class SearchCitiesByName(private val cityRepository: ICityRepository) : ISearchCitiesByName {

    override suspend fun invoke(command: SearchCitiesByNameCommand): List<City> =
        cityRepository.searchCitiesByName(command.name)

}