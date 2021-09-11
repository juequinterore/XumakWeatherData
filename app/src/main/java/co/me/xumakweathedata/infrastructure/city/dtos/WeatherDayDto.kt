package co.me.xumakweathedata.infrastructure.city.dtos

import co.me.domain.value_objects.DayHour
import co.me.domain.value_objects.WeatherDay
import co.me.domain.value_objects.WeatherType
import co.me.domain.value_objects.WeekDay

data class WeatherDayDto(
    val dayOfTheWeek: Int,
    val weatherType: String,
    val low: Int,
    val high: Int,
    val hourlyWeather: Map<Int, HourlyWeatherDto>
) {
    fun toDomain(): WeatherDay = WeatherDay(dayOfTheWeek = WeekDay(dayOfTheWeek),
        weatherType = WeatherType.fromString(weatherType),
        high = high,
        low = low,
        hourlyWeather = hourlyWeather.entries.fold(
            mutableMapOf()
        ) { acc, entry ->
            acc[DayHour(entry.key)] = entry.value.toDomain()
            acc
        })

    companion object
}