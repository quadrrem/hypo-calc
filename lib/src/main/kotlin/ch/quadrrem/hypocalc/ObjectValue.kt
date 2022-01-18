package ch.quadrrem.hypocalc

data class ObjectValue(internal val value: Money) : Comparable<ObjectValue> {

    override fun toString(): String = value.toString()

    override fun compareTo(other: ObjectValue) = value.compareTo(other.value)

    operator fun plus(other: ObjectValue) = ObjectValue(value + other.value)

    companion object {
        fun parse(objectValue: String) = ObjectValue(Money.parse(objectValue))
    }
}