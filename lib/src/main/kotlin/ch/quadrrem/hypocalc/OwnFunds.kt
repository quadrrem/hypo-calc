package ch.quadrrem.hypocalc

data class OwnFunds(
    val ownCapital: List<Fund>,
    val pensionFund: List<Fund>,
) {
    fun total(): Money {
        val ownCapital = sumFunds(this.ownCapital)
        val pensionFund = sumFunds(this.pensionFund)
        return ownCapital + (ownCapital.min(pensionFund))
    }

    private fun sumFunds(funds: List<Fund>) = funds.map { it.amount }.reduce(Money::plus)

    data class Fund(val name: String, val amount: Money)
}