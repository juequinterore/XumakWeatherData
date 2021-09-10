package co.me.xumakweathedata.infrastructure.city

data class WeatherDayDto(
    val dayOfTheWeek: Int,
    val weatherType: String,
    val low: Int,
    val high: Int,
    val hourlyWeather: Map<Int, HourlyWeatherDto>
)