package co.me.domain.entities

import co.me.domain.value_objects.WeatherDay
import co.me.domain.value_objects.XUrl

data class City(
    val id: Int,
    val name: String,
    val imageUrl: XUrl,
    val isTopCity: Boolean,
    val weather: Map<Int, WeatherDay>
)