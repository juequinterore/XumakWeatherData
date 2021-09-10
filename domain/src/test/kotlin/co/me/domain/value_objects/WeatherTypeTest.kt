package co.me.domain.value_objects

import org.junit.Assert.assertEquals
import org.junit.Test

class WeatherTypeTest {
    @Test(expected = IllegalArgumentException::class)
    fun `should not allow creating a non existing WeatherType`() {
        WeatherType.valueOf("notAWeatherType")
    }

    @Test
    fun `should create cloudy WeatherType`() {
        val sunny = WeatherType.fromString("cloudy")
        assertEquals(sunny.value, "cloudy")
    }

    @Test
    fun `should create lightRain WeatherType`() {
        val lightRain = WeatherType.fromString("lightRain")
        assertEquals(lightRain.value, "lightRain")
    }

    @Test
    fun `should create heavyRain WeatherType`() {
        val sunny = WeatherType.fromString("heavyRain")
        assertEquals(sunny.value, "heavyRain")
    }

    @Test
    fun `should create partlyCloudy WeatherType`() {
        val partlyCloudy = WeatherType.fromString("partlyCloudy")
        assertEquals(partlyCloudy.value, "partlyCloudy")
    }

    @Test
    fun `should create snowSleet WeatherType`() {
        val sunny = WeatherType.fromString("snowSleet")
        assertEquals(sunny.value, "snowSleet")
    }

    @Test
    fun `should create sunny WeatherType`() {
        val sunny = WeatherType.fromString("sunny")
        assertEquals(sunny.value, "sunny")
    }

}