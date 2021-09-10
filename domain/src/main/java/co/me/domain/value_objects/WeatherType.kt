package co.me.domain.value_objects

enum class WeatherType(val value: String) {
    CLOUDY("cloudy"),
    LIGHTRAIN("lightRain"),
    HEAVYRAIN("heavyRain"),
    PARTLYCLOUDY("partlyCloudy"),
    SNOWSLEET("snowSleet"),
    SUNNY("sunny");

    companion object {
        fun fromString(value: String): WeatherType {
            return values().first { it.name == value.uppercase() }
        }
    }
}