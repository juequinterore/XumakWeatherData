package co.me.domain.value_objects

@JvmInline
value class WeekDay(val value: Int) {
    init {
        require(value in 0..6) {
            "$value is not a valid week day. Must be between [0, 6]"
        }
    }
}