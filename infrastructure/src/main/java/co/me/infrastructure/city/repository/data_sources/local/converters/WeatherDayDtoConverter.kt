package co.me.infrastructure.city.repository.data_sources.local.converters

import androidx.room.TypeConverter
import co.me.infrastructure.city.dtos.WeatherDayDto
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class WeatherDayDtoConverter {

    @TypeConverter
    fun fromWeatherDayDto(value: Map<Int, WeatherDayDto>?): String? = Gson().toJson(value)

    @TypeConverter
    fun toWeatherDayDto(value: String?): Map<Int, WeatherDayDto>? =
        Gson().fromJson(value, object :
            TypeToken<Map<@kotlin.jvm.JvmSuppressWildcards Int, @kotlin.jvm.JvmSuppressWildcards WeatherDayDto>>() {}.type)

}