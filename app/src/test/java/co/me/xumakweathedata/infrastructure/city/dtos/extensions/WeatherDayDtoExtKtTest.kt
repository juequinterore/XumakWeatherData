package co.me.xumakweathedata.infrastructure.city.dtos.extensions

import co.me.xumakweathedata.infrastructure.city.dtos.HourlyWeatherDto
import co.me.xumakweathedata.infrastructure.city.dtos.WeatherDayDto
import com.google.gson.Gson
import com.google.gson.JsonElement
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.File
import java.nio.file.Paths

class WeatherDayDtoExtKtTest {
    @Test
    fun `should create a WeatherDayDto from a JsonElement`() {

        //Arrange
        val path = Paths.get("").toAbsolutePath().toString()
        val weatherDayJson =
            File("$path/src/test/java/co/me/xumakweathedata/infrastructure/city/fixtures/weatherDay.json").readText()
        val jsonElement = Gson().fromJson(weatherDayJson, JsonElement::class.java)

        //Act
        val weatherDay = WeatherDayDto.fromJsonElement(jsonElement)

        //Assert
        val expectedWeatherDay = WeatherDayDto(
            dayOfTheWeek = 0,
            weatherType = "sunny",
            low = 87,
            high = 110,
            hourlyWeather = mapOf(
                0 to HourlyWeatherDto(
                    weatherType = "sunny",
                    hour = 0,
                    rainChance = 0.0,
                    temperature = 96,
                    humidity = 0.1,
                    windSpeed = 6
                ),
                1 to HourlyWeatherDto(
                    weatherType = "sunny",
                    hour = 1,
                    rainChance = 0.0,
                    temperature = 95,
                    humidity = 0.1,
                    windSpeed = 6
                ),
                2 to HourlyWeatherDto(
                    weatherType = "sunny",
                    hour = 2,
                    rainChance = 0.0,
                    temperature = 94,
                    humidity = 0.1,
                    windSpeed = 6
                )
            )
        )

        assertEquals(weatherDay, expectedWeatherDay)

    }
}