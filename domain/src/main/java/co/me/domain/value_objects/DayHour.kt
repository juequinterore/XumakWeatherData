package co.me.domain.value_objects

@JvmInline
value class DayHour(val value: Int) {
    init {
        require(value in 0..23) {
            "$value is not a valid day hour. Must be between [0, 23]"
        }
    }

    fun to12HoursString(): String {
        val amPm = if (value < 12) "AM" else "PM"
        val hour = if (value < 13) value else value - 12

        return "$hour$amPm"
    }
}