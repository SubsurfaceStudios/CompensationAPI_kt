import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.10"
    kotlin("plugin.serialization") version "1.5.10"
    application
}

group = "me.jai"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

    // ktor
    implementation("io.ktor:ktor-server-core:1.6.7")
    implementation("io.ktor:ktor-server-tomcat:1.6.7")

    // ktor plugins
//    implementation("com.mantono.ktor-rate-limiting:0.1.0")
    implementation("io.ktor:ktor-serialization:1.6.7")
    implementation("io.ktor:ktor-auth:1.6.7")
    implementation("io.ktor:ktor-websockets:1.6.7")

    // jetbrains loves you and me alike
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")
    implementation("org.jetbrains.exposed:exposed-core:0.37.3")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.37.3")

    // misc
    implementation("com.ToxicBakery.library.bcrypt:bcrypt:1.0.9")
    implementation("ch.qos.logback:logback-classic:1.2.5")


}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}