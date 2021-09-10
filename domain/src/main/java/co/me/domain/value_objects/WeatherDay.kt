package co.me.domain.value_objects

data class WeatherDay(
    val dayOfTheWeek: WeekDay,
    val low: Int,
    val high: Int,
    val weatherType: WeatherType,
    val hourlyWeather: Map<DayHour, HourlyWeather>
)