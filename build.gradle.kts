import com.google.cloud.tools.gradle.appengine.appyaml.AppEngineAppYamlExtension

val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project

plugins {
    kotlin("jvm") version "1.9.23"
    id("io.ktor.plugin") version "2.3.10"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.23"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("com.google.cloud.tools.appengine") version "2.4.2"
}

group = "com.damjancoric"
version = "1.0.0"

tasks.register<Exec>("tailwindcss") {
    commandLine("npx", "tailwindcss", "-i", "src/main/resources/styles.css", "-o", "src/main/resources/files/tailwind/styles.css")
}
tasks.register<JavaExec>("runDev") {
    mainClass = "io.ktor.server.netty.EngineMain"
    classpath = sourceSets["main"].runtimeClasspath
    args = listOf("-config=application-dev.conf")
}

tasks.register<JavaExec>("runProd") {
    mainClass = "io.ktor.server.netty.EngineMain"
    classpath = sourceSets["main"].runtimeClasspath
    args = listOf("-config=application-prod.conf")
}
configure<AppEngineAppYamlExtension> {
    stage {
        setArtifact("build/libs/${project.name}-all.jar")
    }
    deploy {
        version = "GCLOUD_CONFIG"
        projectId = "GCLOUD_CONFIG"
    }
}
tasks.named("runDev") {
    dependsOn("tailwindcss")
}
tasks.named("run") {
    dependsOn("tailwindcss")
}

application {
    mainClass.set("com.damjancoric.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.ktor:ktor-server-html-builder-jvm")
    implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.10.1")
    implementation("io.ktor:ktor-server-webjars-jvm")
    implementation("org.webjars:jquery:3.2.1")
    implementation("io.ktor:ktor-server-host-common-jvm")
    implementation("io.ktor:ktor-server-resources")
    implementation("io.ktor:ktor-server-netty-jvm")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    testImplementation("io.ktor:ktor-server-tests-jvm")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
    implementation("io.ktor:ktor-server-hsts:$ktor_version")
    implementation("io.ktor:ktor-server-http-redirect:$ktor_version")
    implementation("io.ktor:ktor-server-forwarded-header:$ktor_version")
}
