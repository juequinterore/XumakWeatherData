package co.me.domain.city.use_cases

import co.me.domain.city.ICityRepository
import co.me.domain.entities.City
import co.me.domain.use_cases.IQueryUseCase

data class SearchCityByNameCommand(val name: String)

interface ISearchCityByName : IQueryUseCase<SearchCityByNameCommand, City?>

class SearchCityByName(private val cityRepository: ICityRepository) : ISearchCityByName {

    override suspend fun invoke(command: SearchCityByNameCommand): City? =
        cityRepository.searchCityByName(command.name)

}