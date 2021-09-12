package co.me.domain.entities

import co.me.domain.value_objects.WeatherDay
import co.me.domain.value_objects.WeekDay
import co.me.domain.value_objects.XUrl

data class City(
    val id: Int,
    var name: String,
    var imageUrl: XUrl,
    val adminCode1: String,
    var weather: Map<WeekDay, WeatherDay>
) {
    val fullName: String
        get() {
            return if (name.isNotBlank() && adminCode1.isNotEmpty()) {
                "${name}, ${adminCode1}"
            } else if (name.isNotBlank() && adminCode1.isBlank()) {
                name
            } else if (name.isBlank() && adminCode1.isNotBlank()) {
                adminCode1
            } else {
                ""
            }
        }

    fun getWeatherForDay(weekDay: WeekDay): WeatherDay? = weather[weekDay]
}