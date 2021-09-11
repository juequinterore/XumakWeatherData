package co.me.xumakweathedata.infrastructure.city.dtos

data class CityDto(
    val geonameid: Int,
    val name: String,
    val imageUrl: String,
    val isTopCity: Boolean,
    val weather: Map<Int, WeatherDayDto>
)


