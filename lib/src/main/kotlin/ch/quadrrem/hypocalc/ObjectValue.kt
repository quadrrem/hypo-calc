package ch.quadrrem.hypocalc

data class ObjectValue(internal val value: Money) {

    override fun toString(): String = value.toString()

    companion object {
        fun parse(objectValue: String) = ObjectValue(Money.parse(objectValue))
    }
}