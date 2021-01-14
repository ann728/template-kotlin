import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.4.1"
    id("io.spring.dependency-management") version "1.0.10.RELEASE"

    kotlin("jvm") version "1.4.21"
    kotlin("plugin.spring") version "1.4.21"
    kotlin("kapt") version "1.3.61"
}

group = "jp.co.casareal"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
    maven("https://oss.sonatype.org/content/repositories/snapshots/")
}

dependencies {
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
    implementation("org.seasar.doma.boot:doma-spring-boot-starter:1.5.0")
    // Doma
    implementation("org.seasar.doma:doma:2.24.0")
    kapt("org.seasar.doma:doma:2.24.0")

    // PostgewSQL
    implementation("org.postgresql:postgresql")

    // Webjars
    implementation("org.webjars:jquery:3.5.1")
    implementation("org.webjars:bootstrap:4.5.3")
    implementation("org.webjars:font-awesome:5.15.1")
}

val compileKotlin: KotlinCompile by tasks

kapt {
    arguments {
        arg("doma.resources.dir", compileKotlin.destinationDir)
    }
}

tasks.register("copyDomaResources",Sync::class){
    from("src/main/resources")
    into(compileKotlin.destinationDir)
    include("doma.compile.config")
    include("META-INF/**/*.sql")
    include("META-INF/**/*.script")
}

tasks.withType<KotlinCompile> {
    dependsOn(tasks.getByName("copyDomaResources"))
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
