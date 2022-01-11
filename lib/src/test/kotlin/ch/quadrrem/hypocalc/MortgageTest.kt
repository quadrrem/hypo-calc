package ch.quadrrem.hypocalc

import ch.quadrrem.hypocalc.GrossIncomeMother.GI_100_000
import ch.quadrrem.hypocalc.ObjectValueMother.OV_0_5_MIO
import ch.quadrrem.hypocalc.ObjectValueMother.OV_1_MIO
import ch.quadrrem.hypocalc.ObjectValueMother.OV_2_MIO
import ch.quadrrem.hypocalc.OwnFundsMother.OF_200_000
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldMatch
import org.javamoney.moneta.Money
import java.lang.RuntimeException

class MortgageTest : StringSpec({

    val config = TestHelper.config("simple")

    "mortgage for 0.5 Mio - no second mortgage" {
        val mortgage = Mortgage.of(config, OV_0_5_MIO, OF_200_000)

        mortgage.first shouldBe Money.parse("CHF 300000")
        mortgage.second shouldBe Money.parse("CHF 0")
        mortgage.isAffordable(GI_100_000).shouldBeTrue()
    }


    "mortgage for 1. Mio" {
        val mortgage = Mortgage.of(config, OV_1_MIO, OF_200_000)

        mortgage.first shouldBe Money.parse("CHF 670000")
        mortgage.second shouldBe Money.parse("CHF 130000")
        mortgage.isAffordable(GI_100_000).shouldBeFalse()
    }


    "mortgage for 2 Mio - fails for too high mortgage" {
        shouldThrow<RuntimeException> { Mortgage.of(config, OV_2_MIO, OF_200_000)  }
            .message shouldMatch ".+ is higher than maxPercentage ${config.mortgage.maxPercentage}"
    }
})
