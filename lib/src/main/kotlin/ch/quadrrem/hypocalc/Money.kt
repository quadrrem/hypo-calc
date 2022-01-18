package ch.quadrrem.hypocalc

import java.math.BigDecimal
import java.util.*
import javax.money.CurrencyUnit
import javax.money.MonetaryAmount
import javax.money.format.MonetaryFormats
import org.javamoney.moneta.Money as JavaMoney

class Money private constructor(private val money: JavaMoney) : MonetaryAmount by money {

    val numberStripped: BigDecimal = money.numberStripped

    override fun add(augend: MonetaryAmount) = Money(money.add(augend))

    operator fun plus(other: MonetaryAmount): Money = add(other)

    override fun subtract(subtrahend: MonetaryAmount) = Money(money.subtract(subtrahend))

    operator fun minus(subtrahend: MonetaryAmount) = subtract(subtrahend)

    override fun divide(divisor: Number) = Money(money.divide(divisor))

    override fun multiply(multiplicand: Number) = Money(money.multiply(multiplicand))

    fun min(other: Money): Money = if (this < other) this else other

    fun max(other: Money): Money = if (this > other) this else other

    override fun toString(): String = FORMAT.format(money)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Money

        if (money != other.money) return false

        return true
    }

    override fun hashCode(): Int {
        return money.hashCode()
    }

    companion object {
        private val FORMAT = MonetaryFormats.getAmountFormat(Locale("de", "CH"))

        fun zero(currency: CurrencyUnit) = Money(JavaMoney.of(BigDecimal.ZERO, currency))

        fun parse(amount: String) = Money(JavaMoney.parse(amount, FORMAT))
    }
}