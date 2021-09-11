package co.me.xumakweathedata.infrastructure.city.dtos.extensions

import co.me.xumakweathedata.infrastructure.city.dtos.HourlyWeatherDto
import co.me.xumakweathedata.infrastructure.city.dtos.WeatherDayDto
import com.google.gson.JsonElement

fun WeatherDayDto.Companion.fromJsonElement(weatherDayDtoJsonElement: JsonElement): WeatherDayDto {
    val weatherDayJson = weatherDayDtoJsonElement.asJsonObject

    val dayOfTheWeek = weatherDayJson?.get("dayOfTheWeek")?.asInt
    val weatherType = weatherDayJson?.get("weatherType")?.asString
    val low = weatherDayJson?.get("low")?.asInt
    val high = weatherDayJson?.get("high")?.asInt

    val hourlyWeatherJsonArray = weatherDayJson?.get("hourlyWeather")?.asJsonArray
    val hourlyWeather =
        hourlyWeatherJsonArray?.fold(mutableMapOf<Int, HourlyWeatherDto>()) { acc, item ->
            val hourlyWeatherDto = HourlyWeatherDto.fromJsonElement(item)
            acc[hourlyWeatherDto.hour] = hourlyWeatherDto
            acc
        }

    return WeatherDayDto(
        dayOfTheWeek = dayOfTheWeek!!,
        weatherType = weatherType!!,
        low = low!!,
        high = high!!,
        hourlyWeather = hourlyWeather!!
    )
}