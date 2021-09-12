package co.me.domain.entities

import co.me.domain.value_objects.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class CityTest {

    @Test
    fun `should return weather for requested day`() {
        //Arrange
        val weatherDays = mapOf(
            WeekDay(0) to WeatherDay(
                dayOfTheWeek = WeekDay(0),
                low = 25,
                high = 75,
                weatherType = WeatherType.SUNNY,
                hourlyWeather = mapOf(
                    DayHour(0) to HourlyWeather(
                        rainChance = Probability(0.2),
                        hour = DayHour(0),
                        humidity = Probability(0.15),
                        weatherType = WeatherType.CLOUDY,
                        windSpeed = 32.0,
                        temperature = 26
                    ),
                    DayHour(1) to HourlyWeather(
                        rainChance = Probability(0.2),
                        hour = DayHour(1),
                        humidity = Probability(0.15),
                        weatherType = WeatherType.CLOUDY,
                        windSpeed = 32.0,
                        temperature = 26
                    ),
                    DayHour(2) to HourlyWeather(
                        rainChance = Probability(0.2),
                        hour = DayHour(2),
                        humidity = Probability(0.15),
                        weatherType = WeatherType.SNOWSLEET,
                        windSpeed = 32.0,
                        temperature = 26
                    )
                )
            ),
            WeekDay(1) to WeatherDay(
                dayOfTheWeek = WeekDay(1),
                low = 11,
                high = 23,
                weatherType = WeatherType.CLOUDY,
                hourlyWeather = mapOf(
                    DayHour(0) to HourlyWeather(
                        rainChance = Probability(0.4),
                        hour = DayHour(0),
                        humidity = Probability(0.15),
                        weatherType = WeatherType.CLOUDY,
                        windSpeed = 32.0,
                        temperature = 26
                    ),
                    DayHour(1) to HourlyWeather(
                        rainChance = Probability(0.2),
                        hour = DayHour(1),
                        humidity = Probability(0.15),
                        weatherType = WeatherType.CLOUDY,
                        windSpeed = 32.0,
                        temperature = 26
                    ),
                    DayHour(2) to HourlyWeather(
                        rainChance = Probability(0.2),
                        hour = DayHour(2),
                        humidity = Probability(0.15),
                        weatherType = WeatherType.SUNNY,
                        windSpeed = 32.0,
                        temperature = 26
                    )
                )
            )
        )

        val city = City(
            id = 45150,
            name = "Medellín",
            adminCode1 = "Antioquia",
            imageUrl = XUrl("https://medellin.gov.co/image.png"),
            weather = weatherDays
        )

        //Act
        val weatherForDay = city.getWeatherForDay(WeekDay(1))

        //Assert
        assertEquals(weatherForDay, weatherDays[WeekDay(1)])

    }

    @Test
    fun `should return null if requested day is not filled`() {
        //Arrange
        val weatherDays = mapOf(
            WeekDay(0) to WeatherDay(
                dayOfTheWeek = WeekDay(0),
                low = 25,
                high = 75,
                weatherType = WeatherType.SUNNY,
                hourlyWeather = mapOf(
                    DayHour(0) to HourlyWeather(
                        rainChance = Probability(0.2),
                        hour = DayHour(0),
                        humidity = Probability(0.15),
                        weatherType = WeatherType.CLOUDY,
                        windSpeed = 32.0,
                        temperature = 26
                    ),
                    DayHour(1) to HourlyWeather(
                        rainChance = Probability(0.2),
                        hour = DayHour(1),
                        humidity = Probability(0.15),
                        weatherType = WeatherType.CLOUDY,
                        windSpeed = 32.0,
                        temperature = 26
                    ),
                    DayHour(2) to HourlyWeather(
                        rainChance = Probability(0.2),
                        hour = DayHour(2),
                        humidity = Probability(0.15),
                        weatherType = WeatherType.SNOWSLEET,
                        windSpeed = 32.0,
                        temperature = 26
                    )
                )
            )
        )

        val city = City(
            id = 45150,
            name = "Medellín",
            adminCode1 = "Antioquia",
            imageUrl = XUrl("https://medellin.gov.co/image.png"),
            weather = weatherDays
        )

        //Act
        val weatherForDay = city.getWeatherForDay(WeekDay(1))

        //Assert
        assertNull(weatherForDay)

    }

}