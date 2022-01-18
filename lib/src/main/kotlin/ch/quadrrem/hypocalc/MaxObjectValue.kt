package ch.quadrrem.hypocalc

class MaxObjectValue(
    private val config: Config,
    private val minObjectValue: ObjectValue,
    private val increase: ObjectValue
) {

    fun maxObjectValueFor(grossIncome: GrossIncome, ownFunds: OwnFunds): ObjectValue = objectValues()
        .takeWhile { mortgageFor(it, ownFunds).isAffordable(grossIncome) }
        .last()

    private fun mortgageFor(objectValue: ObjectValue, ownFunds: OwnFunds) =
        Mortgage.of(config, objectValue, ownFunds)

    private fun objectValues() = generateSequence(minObjectValue) { it + increase }
}