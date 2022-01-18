package ch.quadrrem.hypocalc.max

import ch.quadrrem.hypocalc.Mortgage
import ch.quadrrem.hypocalc.ObjectValue

data class MaxObjectValue(
    internal val value: ObjectValue,
    val mortgage: Mortgage
) : Comparable<MaxObjectValue> {
    override fun toString(): String = value.toString()

    override fun compareTo(other: MaxObjectValue) = value.compareTo(other.value)

}