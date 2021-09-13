package co.me.domain.city.use_cases

import co.me.domain.city.ICityRepository
import co.me.domain.entities.City
import co.me.domain.use_cases.IQueryUseCase

data class SearchCityByNameCommand(val name: String)

interface ISearchCityByName : IQueryUseCase<SearchCityByNameCommand, City?>

class SearchCityByName(
    private val cityRepository: ICityRepository,
    private val getCityWeather: IGetCityWeather
) : ISearchCityByName {

    override suspend fun invoke(command: SearchCityByNameCommand): City? {
        val city = cityRepository.searchCityByName(command.name)

        fillCityWeather(city)

        return city
    }

    private suspend fun fillCityWeather(city: City?) =
        city?.let {
            val cityWeather = getCityWeather(GetCityWeatherCommand(it.id))
            it.weather = cityWeather
        }


}