package co.me.domain.entities

import co.me.domain.value_objects.WeatherDay
import co.me.domain.value_objects.XUrl

class City(
    val id: Double,
    val name: String,
    imageUrl: XUrl,
    weather: Map<Int, WeatherDay>
)