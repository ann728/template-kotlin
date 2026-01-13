import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.yaml.snakeyaml.Yaml



val propertiesFileSuffix = "-dev"
val propertiesFile =
    file("$projectDir/src/main/resources/config/application$propertiesFileSuffix.yml")

val applicationProperties: Map<String, Any> =
    Yaml().load(propertiesFile.inputStream())
        ?: error("application-dev.yml を読み込めません")

val springProperties =
    applicationProperties["spring"] as? Map<*, *>
        ?: error("spring が見つかりません")

val datasource =
    springProperties["datasource"] as? Map<*, *>
        ?: error("spring.datasource が見つかりません")

val flywayProperties =
    applicationProperties["flyway"] as? Map<*, *>
        ?: error("flyway が見つかりません")


plugins {
    id("org.springframework.boot") version "2.7.18"
    id("io.spring.dependency-management") version "1.1.4"

    id("org.flywaydb.flyway") version "9.22.3"

    kotlin("jvm") version "1.9.23"
    kotlin("plugin.spring") version "1.9.23"
    kotlin("kapt") version "1.9.23"
}

group = "jp.co.casareal"
version = "0.0.1-SNAPSHOT"


java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "21"
        freeCompilerArgs += "-Xjsr305=strict"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

repositories {
    mavenCentral()
}


dependencies {
    val domaSpringVersion = "1.5.0"
    val domaVersion = "2.44.3"

    // Spring
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    // Kotlin
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // JSON
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    // Doma
    implementation("org.seasar.doma.boot:doma-spring-boot-starter:$domaSpringVersion")
    implementation("org.seasar.doma:doma-kotlin:$domaVersion")
    kapt("org.seasar.doma:doma-processor:$domaVersion")

    // PostgreSQL
    implementation("org.postgresql:postgresql")

    // Flyway（Gradle + Spring 両対応）
    implementation("org.flywaydb:flyway-core")



}


flyway {
    url = flywayProperties["url"] as String
    user = flywayProperties["user"] as String
    password = flywayProperties["password"] as String
    schemas = arrayOf(flywayProperties["schemas"] as String)
    locations = arrayOf(flywayProperties["locations"] as String)
}

buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("org.yaml:snakeyaml:2.2")
    }
}
tasks.withType<ProcessResources> {
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}

kapt {
    arguments {
        arg("doma.resources.dir", file("src/main/resources"))
        arg("doma.kotlin.enabled", "true")
    }
}
kotlin {
    sourceSets.main {
        kotlin.srcDir("build/generated/source/kapt/main")
    }
}


