plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("io.cucumber:cucumber-java:7.21.1")
    testImplementation("io.cucumber:cucumber-junit:7.21.1")
    testImplementation("io.cucumber:cucumber-junit-platform-engine:latest.release")
    testImplementation("org.junit.platform:junit-platform-suite:latest.release")
}

tasks.test {
    useJUnitPlatform()
}