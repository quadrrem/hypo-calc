package ch.quadrrem.hypocalc

import ch.quadrrem.hypocalc.MoneyMother.CHF
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import org.javamoney.moneta.Money

class OwnFundsTest : StringSpec({

    "own funds total" {

        OwnFunds(
            ownCapital = listOf(
                OwnFunds.Fund(
                    name = "cash",
                    amount = Money.parse("CHF 500.0")
                ),
                OwnFunds.Fund(
                    name = "bank",
                    amount = Money.parse("CHF 500.0")
                )
            ),
            pensionFund = listOf(
                OwnFunds.Fund(
                    name = "pension1",
                    amount = Money.parse("CHF 1000.0")
                )
            )
        ).total() shouldBe Money.parse("CHF 2000")
    }

    "pension funds can make 50% at most" {
        OwnFunds(
            ownCapital = listOf(
                OwnFunds.Fund(
                    name = "cash",
                    amount = Money.parse("CHF 750.0")
                ),
            ),
            pensionFund = listOf(
                OwnFunds.Fund(
                    name = "pension1",
                    amount = Money.parse("CHF 1000.0")
                )
            )
        ).total() shouldBe Money.parse("CHF 1500")
    }

    "own capital funds can make more than 50%" {
        OwnFunds(
            ownCapital = listOf(
                OwnFunds.Fund(
                    name = "cash",
                    amount = Money.parse("CHF 750.0")
                ),
            ),
            pensionFund = listOf(
                OwnFunds.Fund(
                    name = "pension1",
                    amount = Money.parse("CHF 500.0")
                )
            )
        ).total() shouldBe Money.parse("CHF 1250")
    }

})
