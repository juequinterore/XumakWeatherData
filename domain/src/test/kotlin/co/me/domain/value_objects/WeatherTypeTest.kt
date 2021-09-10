package co.me.domain.value_objects

import junit.framework.Assert.assertEquals
import org.junit.Test

class WeatherTypeTest {
    @Test(expected = IllegalArgumentException::class)
    fun `should not allow creating a non existing WeatherType`() {
        WeatherType.valueOf("notAWeatherType")
    }

    @Test
    fun `should create sunny WeatherType`() {
        val sunny = WeatherType.fromString("sunny")
        assertEquals(sunny.value, "sunny")
    }

    @Test
    fun `should create cloudy WeatherType`() {
        val sunny = WeatherType.fromString("cloudy")
        assertEquals(sunny.value, "cloudy")
    }

    @Test
    fun `should create snowSleet WeatherType`() {
        val sunny = WeatherType.fromString("snowSleet")
        assertEquals(sunny.value, "snowSleet")
    }

    @Test
    fun `should create heavyRain WeatherType`() {
        val sunny = WeatherType.fromString("heavyRain")
        assertEquals(sunny.value, "heavyRain")
    }
}