package co.me.xumakweathedata.infrastructure.city.repository.data_sources

import co.me.xumakweathedata.infrastructure.city.dtos.CityDto
import co.me.xumakweathedata.infrastructure.city.dtos.WeatherDayDto

interface ICityRemoteDataSource {
    fun searchCityByName(cityName: String): CityDto?
    fun getCityWeather(cityId: Int): Map<Int, WeatherDayDto>
}