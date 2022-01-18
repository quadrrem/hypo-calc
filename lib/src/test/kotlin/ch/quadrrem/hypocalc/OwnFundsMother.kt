package ch.quadrrem.hypocalc

object OwnFundsMother {

    val OF_100_000 = OwnFunds(
        ownCapital = listOf(OwnFunds.Fund("cash", Money.parse("CHF 50000"))),
        pensionFund = listOf(OwnFunds.Fund("cash", Money.parse("CHF 50000")))
    )

    val OF_200_000 = OwnFunds(
        ownCapital = listOf(OwnFunds.Fund("cash", Money.parse("CHF 100000"))),
        pensionFund = listOf(OwnFunds.Fund("cash", Money.parse("CHF 100000")))
    )


    val OF_300_000 = OwnFunds(
        ownCapital = listOf(OwnFunds.Fund("cash", Money.parse("CHF 150000"))),
        pensionFund = listOf(OwnFunds.Fund("cash", Money.parse("CHF 150000")))
    )


    val OF_350_000 = OwnFunds(
        ownCapital = listOf(OwnFunds.Fund("cash", Money.parse("CHF 175000"))),
        pensionFund = listOf(OwnFunds.Fund("cash", Money.parse("CHF 175000")))
    )
}