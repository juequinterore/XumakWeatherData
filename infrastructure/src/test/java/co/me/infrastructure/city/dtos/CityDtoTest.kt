package co.me.infrastructure.city.dtos

import co.me.domain.entities.City
import co.me.domain.value_objects.*
import org.junit.Assert.assertEquals
import org.junit.Test

class CityDtoTest {

    @Test
    fun `should turn a CityDto into City`() {

        //Arrange
        val cityDto = CityDto(
            geonameid = 1234,
            name = "Medellín",
            imageUrl = "https://medellin.co/image.png",
            weather = mapOf(
                6 to WeatherDayDto(
                    dayOfTheWeek = 6,
                    weatherType = "cloudy",
                    low = 12,
                    high = 32,
                    hourlyWeather = mapOf(
                        0 to HourlyWeatherDto(
                            rainChance = 0.3,
                            hour = 3,
                            humidity = 0.19,
                            weatherType = "cloudy",
                            windSpeed = 23.0,
                            temperature = 39
                        )
                    )
                )
            )
        )

        //Act
        val city = cityDto.toDomain()

        //Assert

        val expectedCity = City(
            id = 1234,
            name = "Medellín",
            imageUrl = XUrl("https://medellin.co/image.png"),
            weather = mapOf(
                WeekDay(6) to WeatherDay(
                    dayOfTheWeek = WeekDay(6),
                    low = 12,
                    high = 32,
                    weatherType = WeatherType.CLOUDY,
                    hourlyWeather = mapOf(
                        DayHour(0) to HourlyWeather(
                            rainChance = Probability(0.3),
                            hour = DayHour(3),
                            humidity = Probability(0.19),
                            weatherType = WeatherType.CLOUDY,
                            windSpeed = 23.0,
                            temperature = 39
                        )
                    )
                )
            )
        )

        assertEquals(city, expectedCity)
    }

}