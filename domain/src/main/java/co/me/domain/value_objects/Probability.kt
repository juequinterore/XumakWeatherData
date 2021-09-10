package co.me.domain.value_objects

@JvmInline
value class Probability(val value: Double) {
    init {
        require(value in 0.0..1.0) {
            "$value must be between [0, 1]"
        }
    }
}