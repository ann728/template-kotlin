import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

//使用するapplication.ymlの設定("dev"の場合"-dev"と指定)
val propertiesFileSuffix = "-dev"
val propertiesFile = File("$projectDir/src/main/resources/config/application$propertiesFileSuffix.yml").inputStream()
val applicationProperties: Map<String, Any> = org.yaml.snakeyaml.Yaml().load(propertiesFile) ?: throw IllegalArgumentException()

buildscript {
    val postgresqlVersion = "42.2.19"
    val snakeyamlVersion = "1.28"
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("org.postgresql:postgresql:$postgresqlVersion")
        classpath("org.yaml:snakeyaml:$snakeyamlVersion")
    }
}


plugins {
    id("org.springframework.boot") version "2.4.1"
    id("io.spring.dependency-management") version "1.0.10.RELEASE"
    id("org.seasar.doma.codegen") version "1.2.1"
    id("org.seasar.doma.compile") version "1.1.0"

    kotlin("jvm") version "1.4.21"
    kotlin("plugin.spring") version "1.4.21"
    kotlin("kapt") version "1.3.61"
}

group = "jp.co.casareal"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

dependencies {
    val domaSpringVersion = "1.5.0"
    val domaVersion = "2.44.3"
    val jqueryVersion = "3.5.1"
    val bootstrapVersion = "4.5.3"
    val fontAwesomeVersion = "5.15.1"

    // Spring
    implementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // json
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    // Thymeleaf
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("nz.net.ultraq.thymeleaf:thymeleaf-layout-dialect:2.4.1")
    implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity5")

    // Spring Security
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.security:spring-security-test")
    kapt("org.springframework.boot:spring-boot-configuration-processor")

    // Spring x Doma
    implementation("org.seasar.doma.boot:doma-spring-boot-starter:${domaSpringVersion}")
    // Doma
    kapt("org.seasar.doma:doma-processor:${domaVersion}")
    implementation("org.seasar.doma:doma-kotlin:${domaVersion}")

    // PostgewSQL
    implementation("org.postgresql:postgresql")

    // Webjars
    implementation("org.webjars:jquery:${jqueryVersion}")
    implementation("org.webjars:bootstrap:${bootstrapVersion}")
    implementation("org.webjars:font-awesome:${fontAwesomeVersion}")
}

val springProperties = applicationProperties["spring"] as Map<*, *>
val datasource = springProperties["datasource"] as Map<*, *>
domaCodeGen {
    register("dev") {
        url.set(datasource["url"] as String)
        user.set(datasource["username"] as String)
        password.set(datasource["password"] as String)
        languageType.set(org.seasar.doma.gradle.codegen.desc.LanguageType.KOTLIN)
        entity {
            packageName.set("jp.co.casareal.kotlin.entity") //entityパッケージ
        }
        dao {
            packageName.set("jp.co.casareal.kotlin.dao") //daoパッケージ
        }
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
