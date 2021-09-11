package co.me.infrastructure.city

import co.me.infrastructure.city.dtos.WeatherDayDto
import co.me.infrastructure.city.dtos.extensions.fromJsonElement
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class WeatherDayMapDeserializer : JsonDeserializer<Map<Int, WeatherDayDto>> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Map<Int, WeatherDayDto> {
        val responseJSONObject = json?.asJsonObject

        val weatherObject = responseJSONObject?.getAsJsonObject("weather")
        val daysArray = weatherObject?.getAsJsonArray("days")

        val weatherDayMap = daysArray?.fold(mutableMapOf<Int, WeatherDayDto>()) { acc, item ->
            val weatherDayDto = WeatherDayDto.fromJsonElement(item)
            acc[weatherDayDto.dayOfTheWeek] = weatherDayDto
            acc
        }

        return weatherDayMap!!

    }
}