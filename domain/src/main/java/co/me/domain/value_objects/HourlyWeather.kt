package co.me.domain.value_objects

data class HourlyWeather(
    val rainChance: Probability,
    val hour: DayHour,
    val humidity: Probability,
    val weatherType: WeatherType,
    val windSpeed: Double,
    val temperature: Int
)