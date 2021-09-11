package co.me.infrastructure.city.dtos.extensions

import co.me.infrastructure.city.dtos.HourlyWeatherDto
import com.google.gson.Gson
import com.google.gson.JsonElement
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.File
import java.nio.file.Paths

class HourlyWeatherDtoExtKtTest {

    @Test
    fun `should create HourlyWeatherDto from JsonElement`() {

        //Arrange
        val path = Paths.get("").toAbsolutePath().toString()
        val weatherDayJson =
            File("$path/src/test/java/co/me/infrastructure/city/fixtures/hourlyWeather.json").readText()
        val jsonElement = Gson().fromJson(weatherDayJson, JsonElement::class.java)

        //Act
        val hourlyWeatherDto = HourlyWeatherDto.fromJsonElement(jsonElement)

        //Assert
        val expectedHourlyWeatherDto = HourlyWeatherDto(
            weatherType = "sunny",
            hour = 0,
            rainChance = 0.9,
            temperature = 96,
            humidity = 0.1,
            windSpeed = 6.0
        )

        assertEquals(hourlyWeatherDto, expectedHourlyWeatherDto)

    }

}