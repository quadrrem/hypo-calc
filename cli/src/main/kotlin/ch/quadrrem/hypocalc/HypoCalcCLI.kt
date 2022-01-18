package ch.quadrrem.hypocalc

const val ONE_MIO = 1_000_000
const val HUNDRED_THOUSAND = 100_000

fun main() {
    val grossIncome = GrossIncome.parse("CHF 200000.00")
    val ownFunds = OwnFunds(
        ownCapital = listOf(OwnFunds.Fund("ownCapital", Money.parse("CHF 100000.00"))),
        pensionFund = listOf(OwnFunds.Fund("pensionFunds", Money.parse("CHF 100000.00")))
    )

    val hypoCalc = HypoCalc.default()

    (0..20).forEach { it ->
        val objectValue = ObjectValue.parse("CHF ${ONE_MIO + it * HUNDRED_THOUSAND}")
        val mortgage = hypoCalc.calculateMortgageFor(objectValue, ownFunds)
        val affordable = mortgage.isAffordable(grossIncome)
        val yearlyCosts = mortgage.yearlyCosts

        println("-".repeat(10))
        println("ObjectValue: $objectValue")
        println("Mortgage:")
        println("---------")
        println("Total: ${mortgage.total}")
        println("First: ${mortgage.first}")
        println("Second: ${mortgage.second}")
        println("Percent of ObjectValue: ${mortgage.percent}")
        println()
        println("Affordable: ${if (affordable) "Yes" else "No"}")
        println("Yearly Costs: ${yearlyCosts.total()}")
        println("Percent of GrossIncome: ${yearlyCosts.percentageOfIncome(grossIncome)}")
    }
}