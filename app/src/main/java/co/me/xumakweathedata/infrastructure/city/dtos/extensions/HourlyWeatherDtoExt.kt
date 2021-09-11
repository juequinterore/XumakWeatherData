package co.me.xumakweathedata.infrastructure.city.dtos.extensions

import co.me.xumakweathedata.infrastructure.city.dtos.HourlyWeatherDto
import com.google.gson.JsonElement

fun HourlyWeatherDto.Companion.fromJsonElement(hourlyWeatherDtoJsonElement: JsonElement): HourlyWeatherDto {
    val hourlyWeatherJson = hourlyWeatherDtoJsonElement.asJsonObject

    val rainChance = hourlyWeatherJson?.get("rainChance")?.asDouble
    val hour = hourlyWeatherJson?.get("hour")?.asInt
    val humidity = hourlyWeatherJson?.get("humidity")?.asDouble
    val weatherType = hourlyWeatherJson?.get("weatherType")?.asString
    val windSpeed = hourlyWeatherJson?.get("windSpeed")?.asDouble
    val temperature = hourlyWeatherJson?.get("temperature")?.asInt

    return HourlyWeatherDto(
        rainChance = rainChance!!,
        hour = hour!!,
        humidity = humidity!!,
        weatherType = weatherType!!,
        windSpeed = windSpeed!!,
        temperature = temperature!!
    )
}
