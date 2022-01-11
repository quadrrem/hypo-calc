package ch.quadrrem.hypocalc

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream

@Serializable
data class Config(
    val mortgage: Mortgage,
    val affordability: Affordability,
) {

    @Serializable
    data class Mortgage(
        val maxPercentage: Double,
        val maxPercentageFirst: Double,
        val interest: Double
    ) {
        fun maxPercentage(): Percentage = Percentage.of(maxPercentage)

        fun maxPercentageFirst(): Percentage = Percentage.of(maxPercentageFirst)
    }

    @Serializable
    data class Affordability(
        val maxPercentage: Double,
        val mortgageInterest: Double,
        val upkeepPercentage: Double,
        val repaymentYears: Int
    ) {
        fun maxPercentage(): Percentage = Percentage.of(maxPercentage)

        fun mortgageInterest(): Percentage = Percentage.of(mortgageInterest)

        fun upkeepPercentage() = Percentage.of(upkeepPercentage)
    }

    companion object {
        @OptIn(ExperimentalSerializationApi::class)
        @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
        fun default() = Json.decodeFromStream<Config>(this::class.java.classLoader.getResourceAsStream("config.json"))
    }
}