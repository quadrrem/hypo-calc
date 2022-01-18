package ch.quadrrem.hypocalc.max

import ch.quadrrem.hypocalc.*
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class MaxObjectValueResolverTest : StringSpec({
    val config = TestHelper.config("simple")

    val maxObjectValueResolver = MaxObjectValueResolver(
        config = config,
        minObjectValue = ObjectValueMother.OV_0_1_MIO,
        increase = ObjectValue.parse("CHF 1000")
    )

    "max object value for funds CHF 100'000 and gross income CHF 100'000" {
        maxObjectValueResolver.maxObjectValueFor(
            GrossIncomeMother.GI_100_000,
            OwnFundsMother.OF_100_000
        ).value shouldBe ObjectValue.parse("CHF 500000")
    }

    "max object value for funds CHF 200'000 and gross income CHF 200'000" {
        maxObjectValueResolver.maxObjectValueFor(
            GrossIncomeMother.GI_200_000,
            OwnFundsMother.OF_200_000
        ).value shouldBe ObjectValue.parse("CHF 1000000")
    }

    "max object value for funds CHF 300'000 and gross income CHF 300'000" {
        maxObjectValueResolver.maxObjectValueFor(
            GrossIncomeMother.GI_300_000,
            OwnFundsMother.OF_300_000
        ).value shouldBe ObjectValue.parse("CHF 1499000")
    }

    "max object value for funds CHF 350'000 and gross income CHF 250'000" {
        maxObjectValueResolver.maxObjectValueFor(
            GrossIncomeMother.GI_250_000,
            OwnFundsMother.OF_350_000
        ).value shouldBe ObjectValue.parse("CHF 1504000")
    }
})