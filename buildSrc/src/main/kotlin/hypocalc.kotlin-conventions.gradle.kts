plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
    java
}

group = "ch.quadrrem"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val kotestVersion: String by project

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")
    implementation("org.javamoney:moneta:1.4.2")
    testImplementation("io.kotest:kotest-runner-junit5-jvm:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core-jvm:$kotestVersion")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}