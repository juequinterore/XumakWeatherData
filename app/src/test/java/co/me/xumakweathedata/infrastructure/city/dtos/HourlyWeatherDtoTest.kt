package co.me.xumakweathedata.infrastructure.city.dtos

import co.me.domain.value_objects.DayHour
import co.me.domain.value_objects.HourlyWeather
import co.me.domain.value_objects.Probability
import co.me.domain.value_objects.WeatherType
import org.junit.Assert.assertEquals
import org.junit.Test

class HourlyWeatherDtoTest {

    @Test
    fun `should turn a HourlyWeatherDto into HourlyWeather`() {
        //Arrange
        val hourlyWeatherDto = HourlyWeatherDto(
            rainChance = 0.3,
            hour = 3,
            humidity = 0.19,
            weatherType = "cloudy",
            windSpeed = 23.0,
            temperature = 39
        )

        //Act
        val hourlyWeather = hourlyWeatherDto.toDomain()

        //Assert
        val expectedHourlyWeather = HourlyWeather(
            rainChance = Probability(0.3),
            hour = DayHour(3),
            humidity = Probability(0.19),
            weatherType = WeatherType.CLOUDY,
            windSpeed = 23.0,
            temperature = 39
        )

        assertEquals(hourlyWeather, expectedHourlyWeather)

    }

}