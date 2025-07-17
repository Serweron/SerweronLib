group = "pl.serweron"
version = "1.1.7"
val apiVersion = "1.21"

plugins {
    java
    id("io.papermc.paperweight.userdev") version "2.0.0-beta.17"
    id("io.freefair.lombok") version "8.13.1"
    id("maven-publish")
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            groupId = group.toString()
            artifactId = project.name
            version = version

            from(components["java"])
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

    paperweightDevelopmentBundle("io.papermc.paper:dev-bundle:1.21.7-R0.1-SNAPSHOT")
}

tasks.processResources {
    outputs.upToDateWhen { false }
    filteringCharset = Charsets.UTF_8.name()
    expand("pluginVersion" to version, "pluginAPI" to apiVersion)
}


