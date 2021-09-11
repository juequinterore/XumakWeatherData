package co.me.domain.city.use_cases

import co.me.domain.city.ICityRepository
import co.me.domain.use_cases.IQueryUseCase
import co.me.domain.value_objects.WeatherDay
import co.me.domain.value_objects.WeekDay

data class GetCityWeatherCommand(val cityId: Int)

interface IGetCityWeather : IQueryUseCase<GetCityWeatherCommand, Map<WeekDay, WeatherDay>>

class GetCityWeather(private val cityRepository: ICityRepository) : IGetCityWeather {

    override suspend fun invoke(command: GetCityWeatherCommand): Map<WeekDay, WeatherDay> =
        cityRepository.getCityWeather(cityId = command.cityId)

}

