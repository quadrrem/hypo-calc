package ch.quadrrem.hypocalc

import ch.quadrrem.hypocalc.Percentage.Companion.percentageOf

class YearlyCosts(
    private val mortgage: Mortgage
) {

    fun percentageOfIncome(grossIncome: GrossIncome) = total().percentageOf(grossIncome.value)

    fun total(): Money = interestCosts() + upkeepCosts() + repaymentCosts()

    private fun interestCosts() = affordability().mortgageInterest().of(mortgage.total)

    private fun upkeepCosts() = affordability().upkeepPercentage().of(mortgage.objectValue.value)

    private fun repaymentCosts() = mortgage.second.divide(affordability().repaymentYears)

    private fun affordability() = mortgage.config.affordability
}