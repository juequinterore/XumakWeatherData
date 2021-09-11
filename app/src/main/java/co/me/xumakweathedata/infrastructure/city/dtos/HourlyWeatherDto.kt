package co.me.xumakweathedata.infrastructure.city.dtos

data class HourlyWeatherDto(
    val rainChance: Double,
    val hour: Int,
    val humidity: Double,
    val weatherType: String,
    val windSpeed: Int,
    val temperature: Int
) {
    companion object
}