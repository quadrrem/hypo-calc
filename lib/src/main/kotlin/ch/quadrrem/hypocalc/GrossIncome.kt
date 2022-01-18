package ch.quadrrem.hypocalc

data class GrossIncome(val value: Money) : Comparable<GrossIncome> {

    operator fun plus(other: GrossIncome) = GrossIncome(value + other.value)

    override fun compareTo(other: GrossIncome) = value.compareTo(other.value)

    companion object {
        fun parse(grossIncome: String) = GrossIncome(Money.parse(grossIncome))
    }
}