plugins {
    id("java")
}

group = "io.github.goscouter"
version = figureVersion()

fun figureVersion(): String {
    val version = findProperty("version")
    return version?.toString() ?: "dev"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.apache.logging.log4j:log4j-api:${findProperty("logger.version")}")
    implementation("com.fasterxml.jackson.core:jackson-databind:${findProperty("jackson.version")}")

    compileOnly("org.projectlombok:lombok:${findProperty("lombok.version")}")
    annotationProcessor("org.projectlombok:lombok:${findProperty("lombok.version")}")

    implementation("net.dv8tion:JDA:${findProperty("jda.version")}") {
        exclude(module = "opus-java")
        exclude(module = "tink")
    }

    testImplementation(platform("org.junit:junit-bom:${findProperty("junit.version")}"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}

tasks.jar {
    archiveFileName.set("gs-bot.jar")
}