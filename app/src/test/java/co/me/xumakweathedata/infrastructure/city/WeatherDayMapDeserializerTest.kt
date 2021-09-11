package co.me.xumakweathedata.infrastructure.city

import co.me.xumakweathedata.infrastructure.city.dtos.HourlyWeatherDto
import co.me.xumakweathedata.infrastructure.city.dtos.WeatherDayDto
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import org.junit.Assert.*
import org.junit.Test
import java.io.File
import java.lang.reflect.Type
import java.nio.file.Paths

class WeatherDayMapDeserializerTest {

    @Test
    fun `should convert city weather response into Map of Week day to WeatherDayDto`() {
        //Arrange
        val weatherDayMapDeserializer = WeatherDayMapDeserializer()
        val path = Paths.get("").toAbsolutePath().toString()
        val weatherResponseJson =
            File("$path/src/test/java/co/me/xumakweathedata/infrastructure/city/fixtures/cityWeather.json").readText()

        //Act
        val type: Type =
            object :
                TypeToken<Map<@kotlin.jvm.JvmSuppressWildcards Int, @kotlin.jvm.JvmSuppressWildcards WeatherDayDto>>() {}.type
        val deserializedWeatherDays =
            weatherDayMapDeserializer.deserialize(
                Gson().fromJson(
                    weatherResponseJson,
                    JsonElement::class.java
                ), type, null
            )

        //Assert

        val expectedWeatherDays = mapOf(
            0 to WeatherDayDto(
                dayOfTheWeek = 0,
                weatherType = "sunny",
                low = 87,
                high = 110,
                hourlyWeather = mapOf(
                    2 to HourlyWeatherDto(
                        weatherType = "sunny",
                        rainChance = 0.0,
                        temperature = 94,
                        hour = 2,
                        humidity = 0.1,
                        windSpeed = 6
                    ),
                    14 to HourlyWeatherDto(
                        weatherType = "sunny",
                        rainChance = 0.0,
                        temperature = 108,
                        hour = 14,
                        humidity = 0.1,
                        windSpeed = 6
                    ),
                    15 to HourlyWeatherDto(
                        weatherType = "sunny",
                        rainChance = 0.0,
                        temperature = 109,
                        hour = 15,
                        humidity = 0.1,
                        windSpeed = 6
                    )
                )
            ),
            4 to WeatherDayDto(
                dayOfTheWeek = 4,
                weatherType = "sunny",
                low = 96,
                high = 120,
                hourlyWeather = mapOf(
                    0 to HourlyWeatherDto(
                        weatherType = "sunny",
                        rainChance = 0.0,
                        temperature = 104,
                        hour = 0,
                        humidity = 0.1,
                        windSpeed = 6
                    ),
                    7 to HourlyWeatherDto(
                        weatherType = "sunny",
                        rainChance = 0.0,
                        temperature = 98,
                        hour = 7,
                        humidity = 0.1,
                        windSpeed = 6
                    ),
                    23 to HourlyWeatherDto(
                        weatherType = "sunny",
                        rainChance = 0.0,
                        temperature = 109,
                        hour = 23,
                        humidity = 0.1,
                        windSpeed = 6
                    )
                )
            )
        )

        assertEquals(deserializedWeatherDays, expectedWeatherDays)
    }

}