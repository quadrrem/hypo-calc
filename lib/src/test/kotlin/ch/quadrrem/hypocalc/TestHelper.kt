package ch.quadrrem.hypocalc

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream

object TestHelper {

    @OptIn(ExperimentalSerializationApi::class)
    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    fun config(name: String) =
        Json.decodeFromStream<Config>(this.javaClass.classLoader.getResourceAsStream("$name-config.json"))
}