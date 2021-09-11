package co.me.domain.entities

import co.me.domain.value_objects.WeatherDay
import co.me.domain.value_objects.WeekDay
import co.me.domain.value_objects.XUrl

data class City(
    val id: Int,
    var name: String,
    var imageUrl: XUrl,
    var weather: Map<WeekDay, WeatherDay>
) {
    fun getWeatherForDay(weekDay: WeekDay): WeatherDay? = weather[weekDay]
}