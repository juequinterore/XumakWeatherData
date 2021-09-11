package co.me.xumakweathedata.infrastructure.city

import co.me.xumakweathedata.infrastructure.city.dtos.CityDto
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class CityListDeserializer : JsonDeserializer<List<CityDto>> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): List<CityDto> {
        val responseJsonObject = json?.asJsonObject

        val citiesArray = responseJsonObject?.getAsJsonArray("cities")

        val cityDtos = citiesArray?.map {
            val cityJson = it.asJsonObject
            val geonameid = cityJson?.get("geonameid")?.asInt
            val name = cityJson?.get("name")?.asString
            val imageURLs = cityJson?.get("imageURLs")?.asJsonObject
            val androidImageURLs = imageURLs?.get("androidImageURLs")?.asJsonObject
            val imageUrl = androidImageURLs?.get("xhdpiImageURL")?.asString

            CityDto(
                geonameid = geonameid!!,
                name = name!!,
                imageUrl = imageUrl!!,
                isTopCity = false,
                weather = mapOf()
            )
        }

        return cityDtos ?: emptyList()

    }
}