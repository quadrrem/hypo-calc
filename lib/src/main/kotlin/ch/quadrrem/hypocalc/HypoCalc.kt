package ch.quadrrem.hypocalc

class HypoCalc private constructor(private val config: Config) {

    fun calculateMortgageFor(objectValue: ObjectValue, ownFunds: OwnFunds): Mortgage =
        Mortgage.of(config, objectValue, ownFunds)

    companion object {
        fun default() = withConfig(Config.default())

        fun withConfig(config: Config) = HypoCalc(config)
    }
}