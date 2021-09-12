package co.me.xumakweathedata.extensions

import co.me.domain.value_objects.WeatherType
import co.me.xumakweathedata.R

fun WeatherType.toIcon(): Int {
    return when (this) {
        WeatherType.CLOUDY -> R.drawable.ic_icon_weather_active_ic_cloudy_active
        WeatherType.LIGHTRAIN -> R.drawable.ic_icon_weather_active_ic_light_rain_active
        WeatherType.HEAVYRAIN -> R.drawable.ic_icon_weather_active_ic_heavy_rain_active
        WeatherType.PARTLYCLOUDY -> R.drawable.ic_icon_weather_active_ic_partly_cloudy_active
        WeatherType.SNOWSLEET -> R.drawable.ic_icon_weather_active_ic_snow_sleet_active
        WeatherType.SUNNY -> R.drawable.ic_icon_weather_active_ic_sunny_active
    }
}