plugins {
    kotlin("jvm") version "1.9.22"
    application
}

group = "asteroid4.tileengine"
version = "0.1.0"

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
    mainClass = "asteroid4.tileengine.MainKt"
}

tasks.jar {
    manifest {
        attributes ["Main-Class"] = "asteroid4.tileengine.MainKt"
    }

    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    from(sourceSets.main.get().output)

    dependsOn(configurations.runtimeClasspath)

    from({
        configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
    })

    archiveFileName = "TileEngine.jar"
}
