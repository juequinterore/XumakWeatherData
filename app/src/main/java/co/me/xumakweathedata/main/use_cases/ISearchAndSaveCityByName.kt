package co.me.xumakweathedata.main.use_cases

import co.me.domain.city.ICityRepository
import co.me.domain.city.use_cases.ISearchCityByName
import co.me.domain.city.use_cases.SearchCityByNameCommand
import co.me.domain.use_cases.ICommandUseCase

class SearchAndSaveCityByNameCommand(val cityName: String)


interface ISearchAndSaveCityByName : ICommandUseCase<SearchAndSaveCityByNameCommand>

class SearchAndSaveCityByName(
    private val searchCityByName: ISearchCityByName,
    private val citiesRepository: ICityRepository
) : ISearchAndSaveCityByName {

    override suspend fun invoke(command: SearchAndSaveCityByNameCommand) {
        val city = searchCityByName(SearchCityByNameCommand(command.cityName))
        city?.let {
            citiesRepository.insert(it)
        }
    }

}