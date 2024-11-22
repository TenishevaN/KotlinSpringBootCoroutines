plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
    id("org.springframework.boot") version "3.3.6"
    id("io.spring.dependency-management") version "1.1.6"
}


group = "com.breed"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    // Spring Boot Starters
    implementation ("org.springframework.boot:spring-boot-starter-data-r2dbc")
    implementation ("org.springframework.boot:spring-boot-starter-webflux")

    // Database Drivers
    implementation ("io.r2dbc:r2dbc-h2:1.0.0.RELEASE")
    implementation ("io.r2dbc:r2dbc-postgresql:0.8.13.RELEASE")

    // Kotlin Libraries
    implementation ("org.jetbrains.kotlin:kotlin-reflect")
    implementation ("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.5.2")

    // Jackson Module for Kotlin
    implementation ("com.fasterxml.jackson.module:jackson-module-kotlin:2.16.1")

    // Reactor Kotlin Extensions
    implementation ("io.projectreactor.kotlin:reactor-kotlin-extensions:1.2.2")

    // Development Tools
    developmentOnly ("org.springframework.boot:spring-boot-devtools")

    // Testing Dependencies
    testImplementation ("org.springframework.boot:spring-boot-starter-test")
    testImplementation ("io.projectreactor:reactor-test")
    testImplementation ("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly ("org.junit.platform:junit-platform-launcher")

    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    testImplementation("com.ninja-squad:springmockk:4.0.2")
    testImplementation("io.mockk:mockk:1.12.0")
    testImplementation("org.mockito:mockito-core:4.0.0")
    testImplementation("org.mockito.kotlin:mockito-kotlin:4.0.0")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}


