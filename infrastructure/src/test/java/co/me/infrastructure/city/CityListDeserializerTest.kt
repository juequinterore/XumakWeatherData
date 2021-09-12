package co.me.infrastructure.city

import co.me.infrastructure.city.dtos.CityDto
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.File
import java.lang.reflect.Type
import java.nio.file.Paths

class CityListDeserializerTest {

    @Test
    fun `should convert cities response into List of cities`() {
        //Arrange
        val cityListDeserializer = CityListDeserializer()
        val path = Paths.get("").toAbsolutePath().toString()
        val multiplesCities =
            File("$path/src/test/java/co/me/infrastructure/city/fixtures/multipleCities.json").readText()

        //Act
        val type: Type =
            object : TypeToken<List<@kotlin.jvm.JvmSuppressWildcards CityDto>>() {}.type
        val deserializedCities =
            cityListDeserializer.deserialize(
                Gson().fromJson(
                    multiplesCities,
                    JsonElement::class.java
                ), type, null
            )

        //Assert
        val expectedCities = listOf(
            CityDto(
                geonameid = 4053050,
                name = "Calera",
                adminCode1 = "AL",
                imageUrl = "https://all-the-weather-resources.s3.amazonaws.com/Images/Android_City_Images/xhdpi/img_austin.png",
                weather = emptyMap()
            ),

            CityDto(
                geonameid = 4054852,
                name = "Chelsea",
                adminCode1 = "AL",
                imageUrl = "https://all-the-weather-resources.s3.amazonaws.com/Images/Android_City_Images/xhdpi/img_dallas.png",
                weather = emptyMap()
            ),

            CityDto(
                geonameid = 4049979,
                name = "Birmingham",
                adminCode1 = "AL",
                imageUrl = "https://all-the-weather-resources.s3.amazonaws.com/Images/Android_City_Images/xhdpi/img_austin.png",
                weather = emptyMap()
            ),

            CityDto(
                geonameid = 4055045,
                name = "Childersburg",
                adminCode1 = "AL",
                imageUrl = "https://all-the-weather-resources.s3.amazonaws.com/Images/Android_City_Images/xhdpi/img_sanfranisco.png",
                weather = emptyMap()
            ),

            CityDto(
                geonameid = 4048888,
                name = "Red Chute",
                adminCode1 = "LA",
                imageUrl = "https://all-the-weather-resources.s3.amazonaws.com/Images/Android_City_Images/xhdpi/img_dallas.png",
                weather = emptyMap()
            ),

            CityDto(
                geonameid = 4054996,
                name = "Chickasaw",
                adminCode1 = "AL",
                imageUrl = "https://all-the-weather-resources.s3.amazonaws.com/Images/Android_City_Images/xhdpi/img_austin.png",
                weather = emptyMap()
            )
        )
        println("Deserialized: $deserializedCities")


        assertEquals(deserializedCities, expectedCities)
    }

}