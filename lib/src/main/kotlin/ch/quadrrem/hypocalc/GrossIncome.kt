package ch.quadrrem.hypocalc

data class GrossIncome(val value: Money) {
    companion object {
        fun parse(grossIncome: String) = GrossIncome(Money.parse(grossIncome))
    }
}