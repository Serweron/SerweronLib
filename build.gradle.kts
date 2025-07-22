group = "pl.serweron"
version = "0.5.3"
val apiVersion = "1.21"

plugins {
    java
    id("io.papermc.paperweight.userdev") version "2.0.0-beta.17"
    id("io.freefair.lombok") version "8.13.1"
    id("maven-publish")
}

val sourcesJar by tasks.registering(Jar::class) {
    archiveClassifier.set("sources")
    from(sourceSets.main.get().allSource)
}

val javadocJar by tasks.registering(Jar::class) {
    dependsOn(tasks.javadoc)
    archiveClassifier.set("javadoc")
    from(tasks.javadoc)
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            groupId = group.toString()
            artifactId = project.name
            version = version

            from(components["java"])

            artifact(sourcesJar.get())
            artifact(javadocJar.get())
        }
    }

    repositories {
        maven {
            name = "reposilte"
            url = uri(
                if (version.toString().endsWith("SNAPSHOT"))
                    "https://maven.pcreators.pl/snapshots"
                else
                    "https://maven.pcreators.pl/releases"
            )

            credentials {
                username = System.getenv("REPO_USER")
                password = System.getenv("REPO_PASS")
            }
        }
    }
}

java.toolchain.languageVersion.set(JavaLanguageVersion.of(21))

repositories {
    mavenCentral()
    maven {
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
}

dependencies {
    compileOnly("org.projectlombok:lombok:1.18.38")
    annotationProcessor("org.projectlombok:lombok:1.18.38")

    testCompileOnly("org.projectlombok:lombok:1.18.38")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.38")

    testImplementation("org.junit.jupiter:junit-jupiter:5.13.3")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("org.mockito:mockito-core:5.+")

    paperweightDevelopmentBundle("io.papermc.paper:dev-bundle:1.21.1-R0.1-SNAPSHOT")
}

tasks.processResources {
    outputs.upToDateWhen { false }
    filteringCharset = Charsets.UTF_8.name()
    expand("pluginVersion" to version, "pluginAPI" to apiVersion)
}

tasks.test {
    useJUnitPlatform()
}

