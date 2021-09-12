package co.me.xumakweathedata.main.use_cases

import co.me.domain.city.ICityRepository
import co.me.domain.city.use_cases.IGetAllCities
import co.me.domain.city.use_cases.ISearchCityByName
import co.me.domain.city.use_cases.SearchCityByNameCommand
import co.me.domain.use_cases.ICommandUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

interface IInitialCitiesRequest : ICommandUseCase<Unit>

class InitialCitiesCitiesRequest(
    private val getAllCities: IGetAllCities,
    private val searchCityByName: ISearchCityByName,
    private val citiesRepository: ICityRepository
) :
    IInitialCitiesRequest {
    override suspend fun invoke(command: Unit) {
        getAllCities(Unit).onEach {
            if (it.isEmpty()) {
                requestDefaultCity()
            }
        }.collect()
    }

    private suspend fun requestDefaultCity() {
        val city = searchCityByName(SearchCityByNameCommand("Calera"))
        city?.let {
            citiesRepository.insert(it)
        }
    }

}