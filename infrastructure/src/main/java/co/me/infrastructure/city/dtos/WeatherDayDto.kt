package co.me.infrastructure.city.dtos

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

    companion object {
        fun fromDomain(weatherDay: WeatherDay) =
            WeatherDayDto(dayOfTheWeek = weatherDay.dayOfTheWeek.value,
                weatherType = weatherDay.weatherType.value,
                low = weatherDay.low,
                high = weatherDay.high,
                hourlyWeather = weatherDay.hourlyWeather.entries.fold(
                    mutableMapOf()
                ) { acc, entry ->
                    acc[entry.key.value] = HourlyWeatherDto.fromDomain(entry.value)
                    acc
                })
    }
}