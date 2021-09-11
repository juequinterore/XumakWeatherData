package co.me.infrastructure.city.repository.data_sources.local.converters

import androidx.room.TypeConverter
import co.me.infrastructure.city.dtos.HourlyWeatherDto
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class HourlyWeatherDtoConverter {

    @TypeConverter
    fun fromHourlyWeatherDto(value: Map<Int, HourlyWeatherDto>?): String? = Gson().toJson(value)

    @TypeConverter
    fun toHourlyWeatherDto(value: String?): HourlyWeatherDto? =
        Gson().fromJson(value, object :
            TypeToken<Map<@kotlin.jvm.JvmSuppressWildcards Int, @kotlin.jvm.JvmSuppressWildcards HourlyWeatherDto>>() {}.type)

}