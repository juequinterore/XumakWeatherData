package co.me.xumakweathedata.main.use_cases

import co.me.domain.city.use_cases.IGetAllCities
import co.me.domain.use_cases.ICommandUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

class InitialCitiesRequestCommand(val defaultName: String)

interface IInitialCitiesRequest : ICommandUseCase<InitialCitiesRequestCommand>

class InitialCitiesCitiesRequest(
    private val getAllCities: IGetAllCities,
    private val searchAndSaveCityByName: ISearchAndSaveCityByName
) :
    IInitialCitiesRequest {
    override suspend fun invoke(command: InitialCitiesRequestCommand) {
        getAllCities(Unit).onEach {
            if (it.isEmpty()) {
                searchAndSaveCityByName(SearchAndSaveCityByNameCommand(command.defaultName))
            }
        }.collect()
    }

}