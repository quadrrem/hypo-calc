package ch.quadrrem.hypocalc.max

import ch.quadrrem.hypocalc.*
import java.util.function.Supplier

class MaxObjectValueResolver(
    private val config: Config,
    private val minObjectValue: ObjectValue,
    private val increase: ObjectValue
) {

    fun maxObjectValueFor(grossIncome: GrossIncome, ownFunds: OwnFunds): MaxObjectValue = objectValues()
        .map { MaxObjectValue(it, mortgageFor(it, ownFunds)) }
        .takeWhile { it.mortgage.isAffordable(grossIncome) }
        .last()

    private fun mortgageFor(objectValue: ObjectValue, ownFunds: OwnFunds) =
        Mortgage.of(config, objectValue, ownFunds)

    private fun objectValues() = generateSequence(minObjectValue) { it + increase }

    fun matrixFor(
        grossIncomes: Supplier<Sequence<GrossIncome>>,
        ownFunds: Supplier<Sequence<OwnFunds>>
    ): MaxObjectValueMatrix = MaxObjectValueMatrix(
        rows = grossIncomes.get().map { grossIncome ->
            MaxObjectValueMatrix.Row(
                grossIncome = grossIncome,
                col = ownFunds.get().map { ownFunds ->
                    MaxObjectValueMatrix.Column(
                        ownFunds = ownFunds,
                        maxObjectValue = maxObjectValueFor(grossIncome, ownFunds)
                    )
                }.toList()
            )
        }.toList()
    )

}