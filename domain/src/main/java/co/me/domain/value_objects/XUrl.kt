package co.me.domain.value_objects

val pattern =
    """[(http(s)?):\/\/(www\.)?a-zA-Z0-9@:%._\-\+~#=]{2,256}\.[a-z]{2,6}\b([-a-zA-Z0-9@:%_\+.~#?&\/=]*)""".toRegex()

@JvmInline
value class XUrl(val value: String) {
    init {
        require(pattern.matches(value)) {
            "$value is not a valid url"
        }
    }
}