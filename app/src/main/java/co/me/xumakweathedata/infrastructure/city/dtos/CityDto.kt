package co.me.xumakweathedata.infrastructure.city.dtos

import co.me.domain.entities.City
import co.me.domain.value_objects.WeekDay
import co.me.domain.value_objects.XUrl

data class CityDto(
    val geonameid: Int,
    val name: String,
    val imageUrl: String,
    val isTopCity: Boolean,
    val weather: Map<Int, WeatherDayDto>
) {

    fun toDomain(): City = City(
        id = geonameid,
        name = name,
        imageUrl = XUrl(imageUrl),
        isTopCity = false,
        weather = weather.entries.fold(mutableMapOf()) { acc, entry ->
            acc[WeekDay(entry.key)] = entry.value.toDomain()
            acc
        })

}


