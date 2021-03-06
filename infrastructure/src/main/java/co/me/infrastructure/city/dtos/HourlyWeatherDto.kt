package co.me.infrastructure.city.dtos

import co.me.domain.value_objects.DayHour
import co.me.domain.value_objects.HourlyWeather
import co.me.domain.value_objects.Probability
import co.me.domain.value_objects.WeatherType

data class HourlyWeatherDto(
    val rainChance: Double,
    val hour: Int,
    val humidity: Double,
    val weatherType: String,
    val windSpeed: Double,
    val temperature: Int
) {
    fun toDomain(): HourlyWeather = HourlyWeather(
        rainChance = Probability(rainChance),
        hour = DayHour(hour),
        humidity = Probability(humidity),
        weatherType = WeatherType.fromString(weatherType),
        windSpeed = windSpeed,
        temperature = temperature
    )

    companion object {
        fun fromDomain(hourlyWeather: HourlyWeather) = HourlyWeatherDto(
            rainChance = hourlyWeather.rainChance.value,
            hour = hourlyWeather.hour.value,
            humidity = hourlyWeather.humidity.value,
            weatherType = hourlyWeather.weatherType.value,
            windSpeed = hourlyWeather.windSpeed,
            temperature = hourlyWeather.temperature
        )
    }
}