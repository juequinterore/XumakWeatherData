package co.me.domain.value_objects

enum class WeatherType(val value: String) {
    SUNNY("sunny"),
    CLOUDY("cloudy"),
    SNOWSLEET("snowSleet"),
    HEAVYRAIN("heavyRain");

    companion object {
        fun fromString(value: String): WeatherType {
            return values().first { it.name == value.uppercase() }
        }
    }
}