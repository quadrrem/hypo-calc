package ch.quadrrem.hypocalc

import java.math.BigDecimal
import java.math.RoundingMode
import java.text.NumberFormat

data class Percentage(private val value: BigDecimal) : Comparable<Percentage> {

    fun min(other: Percentage) = if (this < other) this else other

    fun of(amount: Money, amountInPercent: Percentage = HUNDRED_PERCENT): Money =
        amount.divide(amountInPercent.value).multiply(value)

    override fun compareTo(other: Percentage) = value.compareTo(other.value)

    override fun toString(): String = FORMAT.format(value)

    companion object {
        val HUNDRED_PERCENT = Percentage(BigDecimal.ONE)
        private val FORMAT = NumberFormat.getPercentInstance()

        fun of(percentage: Double) = Percentage(BigDecimal.valueOf(percentage))

        fun Money.percentageOf(total: Money) =
            Percentage(BigDecimal.ONE.divide(total.numberStripped, 10, RoundingMode.CEILING).multiply(numberStripped))

    }
}