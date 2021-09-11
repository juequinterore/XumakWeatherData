package co.me.infrastructure.city.repository.data_sources

import co.me.infrastructure.city.dtos.CityDto
import co.me.infrastructure.city.dtos.WeatherDayDto

interface ICityRemoteDataSource {
    fun searchCityByName(cityName: String): CityDto?
    fun getCityWeather(cityId: Int): Map<Int, WeatherDayDto>
}