plugins {
    kotlin("jvm") version "1.9.22"
    application
}

group = "asteroid4"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

application {
    mainClass = "asteroid4.MainKt"
}