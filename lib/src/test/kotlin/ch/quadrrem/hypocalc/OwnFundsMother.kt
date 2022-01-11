package ch.quadrrem.hypocalc

import org.javamoney.moneta.Money

object OwnFundsMother {

    val OF_200_000 = OwnFunds(
        ownCapital = listOf(OwnFunds.Fund("cash", Money.parse("CHF 100000"))),
        pensionFund = listOf(OwnFunds.Fund("cash", Money.parse("CHF 100000")))
    )
}