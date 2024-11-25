val kotlinVersion = "1.9.25"
val postgreVersion = "1.0.3.RELEASE"
val springdocVersion = "1.8.0"
val openapiStarterVersion = "2.7.0"
val mockitokotkinVersion = "4.0.0"
val kotlinxCoroutinesReactorVersion = "1.7.3"
val jacksonModuleKotlinVersion = "2.18.1"
val reactorKotlinExtensionsVersion = "1.2.2"
val springmockkVersion = "4.0.2"
val mockkVersion = "1.13.13"
val kotlinxCoroutinesTestVersion = "1.9.0"
val dependencyManagementVersion = "1.1.6"

plugins {
    val kotlinVersion = "1.9.25"
    val pluginSpringVersion = "1.9.25"
    val springframeworkBootVersion = "3.4.0"
    val dependencyManagementVersion = "1.1.6"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version pluginSpringVersion
    id("org.springframework.boot") version springframeworkBootVersion
    id("io.spring.dependency-management") version dependencyManagementVersion
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
    implementation ("org.springframework.boot:spring-boot-starter-validation")

    // Database Drivers
    implementation("org.postgresql:r2dbc-postgresql:$postgreVersion")

    // Kotlin Libraries
    implementation ("org.jetbrains.kotlin:kotlin-reflect")
    implementation ("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:$kotlinxCoroutinesReactorVersion")

    // Jackson Module for Kotlin
    implementation ("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonModuleKotlinVersion")

    // Reactor Kotlin Extensions
    implementation ("io.projectreactor.kotlin:reactor-kotlin-extensions:$reactorKotlinExtensionsVersion")

    //Swagger
    implementation("org.springdoc:springdoc-openapi-starter-webflux-ui:${openapiStarterVersion}")
    implementation("org.springdoc:springdoc-openapi-starter-common:${openapiStarterVersion}")

    // Development Tools
    developmentOnly ("org.springframework.boot:spring-boot-devtools")

    // Testing Dependencies
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    testImplementation("io.projectreactor:reactor-test")
    testImplementation ("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly ("org.junit.platform:junit-platform-launcher")

    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    testImplementation("com.ninja-squad:springmockk:$springmockkVersion")
    testImplementation("io.mockk:mockk:$mockkVersion")
    testImplementation("org.mockito:mockito-core:$mockitokotkinVersion")
    testImplementation("org.mockito.kotlin:mockito-kotlin:$mockitokotkinVersion")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$kotlinxCoroutinesTestVersion")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
