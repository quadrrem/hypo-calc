package ch.quadrrem.hypocalc

import ch.quadrrem.hypocalc.Percentage.Companion.ZERO_PERCENT
import ch.quadrrem.hypocalc.Percentage.Companion.percentageOf

data class Mortgage private constructor(
    internal val config: Config,
    internal val objectValue: ObjectValue,
    private val ownFunds: OwnFunds
) {

    private val maxPercentageFirstMortgage = config.mortgage.maxPercentageFirst()

    val percent: Percentage

    val first: Money
    val second: Money

    val total: Money
        get() = first + second

    val yearlyCosts = YearlyCosts(this)

    init {
        val neededMoney = objectValue.value.subtract(ownFunds.total)

        if (neededMoney.isNegativeOrZero) {
            percent = ZERO_PERCENT
            first = Money.zero(objectValue.value.currency)
            second = Money.zero(objectValue.value.currency)
        } else {
            percent = percentage(objectValue, neededMoney)
            val (first, second) = splitMortgage(percent, neededMoney)
            this.first = first
            this.second = second
        }
    }

    private fun percentage(objectValue: ObjectValue, mortgage: Money) =
        mortgage.percentageOf(objectValue.value)

    private fun splitMortgage(percentageMortgage: Percentage, sumMortgage: Money): Pair<Money, Money> {
        val percentageFirst = percentageMortgage.min(maxPercentageFirstMortgage)
        val sumFirst = percentageFirst.of(sumMortgage, amountInPercent = percentageMortgage)
        return sumFirst to sumMortgage.subtract(sumFirst)
    }

    fun isAffordable(grossIncome: GrossIncome): Boolean =
        percent <= config.mortgage.maxPercentage() &&
                yearlyCosts.percentageOfIncome(grossIncome) <= config.affordability.maxPercentage()

    companion object {
        fun of(config: Config, objectValue: ObjectValue, ownFunds: OwnFunds) =
            Mortgage(config, objectValue, ownFunds)
    }
}