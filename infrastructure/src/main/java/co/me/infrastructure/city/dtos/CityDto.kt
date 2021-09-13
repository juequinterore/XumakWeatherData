package co.me.infrastructure.city.dtos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import co.me.domain.entities.City
import co.me.domain.value_objects.WeekDay
import co.me.domain.value_objects.XUrl

@Entity(tableName = "cities")
data class CityDto(
    @PrimaryKey @ColumnInfo(name = "id") val geoNameId: Int,
    val name: String,
    val imageUrl: String,
    val adminCode1: String,
    val weather: Map<Int, WeatherDayDto>,
    val insertTimestamp: Long = System.currentTimeMillis(),
) {

    fun toDomain(): City = City(
        id = geoNameId,
        name = name,
        adminCode1 = adminCode1,
        imageUrl = XUrl(imageUrl),
        weather = weather.entries.fold(mutableMapOf()) { acc, entry ->
            acc[WeekDay(entry.key)] = entry.value.toDomain()
            acc
        })

    companion object {
        fun fromDomain(city: City) = CityDto(
            geoNameId = city.id,
            name = city.name,
            imageUrl = city.imageUrl.value,
            adminCode1 = city.adminCode1,
            weather = city.weather.entries.fold(
                mutableMapOf()
            ) { acc, entry ->
                acc[entry.key.value] = WeatherDayDto.fromDomain(entry.value)
                acc
            }

        )
    }

}


