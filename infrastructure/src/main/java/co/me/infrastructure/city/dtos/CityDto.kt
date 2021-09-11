package co.me.infrastructure.city.dtos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import co.me.domain.entities.City
import co.me.domain.value_objects.WeekDay
import co.me.domain.value_objects.XUrl

@Entity(tableName = "cities")
data class CityDto(
    @PrimaryKey @ColumnInfo(name = "id") val geonameid: Int,
    val name: String,
    val imageUrl: String,
    val weather: Map<Int, WeatherDayDto>
) {

    fun toDomain(): City = City(
        id = geonameid,
        name = name,
        imageUrl = XUrl(imageUrl),
        weather = weather.entries.fold(mutableMapOf()) { acc, entry ->
            acc[WeekDay(entry.key)] = entry.value.toDomain()
            acc
        })

    companion object{
        fun fromDomain(city: City) = CityDto(
            geonameid = city.id, name = city.name, imageUrl = city.imageUrl.value, weather = city.weather.entries.fold(
                mutableMapOf()){
                acc, entry ->
                acc[entry.key.value] = WeatherDayDto.fromDomain(entry.value)
                acc
            }

        )
    }

}


