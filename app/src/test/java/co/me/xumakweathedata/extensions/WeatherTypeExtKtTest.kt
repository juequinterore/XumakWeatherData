package co.me.xumakweathedata.extensions

import co.me.domain.value_objects.WeatherType
import co.me.xumakweathedata.R
import org.junit.Assert.assertEquals
import org.junit.Test

class WeatherTypeExtKtTest2 {

    @Test
    fun shouldReturnProperIconPerEachWeatherType() {
        val map = mapOf(
            WeatherType.CLOUDY to R.drawable.ic_icon_weather_active_ic_cloudy_active,
            WeatherType.HEAVYRAIN to R.drawable.ic_icon_weather_active_ic_heavy_rain_active,
            WeatherType.LIGHTRAIN to R.drawable.ic_icon_weather_active_ic_light_rain_active,
            WeatherType.PARTLYCLOUDY to R.drawable.ic_icon_weather_active_ic_partly_cloudy_active,
            WeatherType.SNOWSLEET to R.drawable.ic_icon_weather_active_ic_snow_sleet_active,
            WeatherType.SUNNY to R.drawable.ic_icon_weather_active_ic_sunny_active
        )

        map.entries.onEach {
            assertEquals(it.key.toIcon(), it.value)
        }
    }

}