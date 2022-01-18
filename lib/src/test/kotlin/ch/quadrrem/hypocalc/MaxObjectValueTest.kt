package ch.quadrrem.hypocalc

import ch.quadrrem.hypocalc.GrossIncomeMother.GI_100_000
import ch.quadrrem.hypocalc.GrossIncomeMother.GI_200_000
import ch.quadrrem.hypocalc.GrossIncomeMother.GI_250_000
import ch.quadrrem.hypocalc.GrossIncomeMother.GI_300_000
import ch.quadrrem.hypocalc.OwnFundsMother.OF_100_000
import ch.quadrrem.hypocalc.OwnFundsMother.OF_200_000
import ch.quadrrem.hypocalc.OwnFundsMother.OF_300_000
import ch.quadrrem.hypocalc.OwnFundsMother.OF_350_000
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class MaxObjectValueTest : StringSpec({
    val config = TestHelper.config("simple")

    val maxObjectValue = MaxObjectValue(
        config = config,
        minObjectValue = ObjectValueMother.OV_0_1_MIO,
        increase = ObjectValue.parse("CHF 1000")
    )

    "max object value for funds CHF 100'000 and gross income CHF 100'000" {
        maxObjectValue.maxObjectValueFor(GI_100_000, OF_100_000) shouldBe ObjectValue.parse("CHF 500000")
    }

    "max object value for funds CHF 200'000 and gross income CHF 200'000" {
        maxObjectValue.maxObjectValueFor(GI_200_000, OF_200_000) shouldBe ObjectValue.parse("CHF 1000000")
    }

    "max object value for funds CHF 300'000 and gross income CHF 300'000" {
        maxObjectValue.maxObjectValueFor(GI_300_000, OF_300_000) shouldBe ObjectValue.parse("CHF 1499000")
    }

    "max object value for funds CHF 350'000 and gross income CHF 250'000" {
        maxObjectValue.maxObjectValueFor(GI_250_000, OF_350_000) shouldBe ObjectValue.parse("CHF 1504000")
    }
})
