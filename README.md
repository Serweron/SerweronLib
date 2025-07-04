# 📦 How to Add `SerweronLib` to Your Plugin

## ✅ 1. Add the Repository

In your `build.gradle.kts`, add the Maven repository:

```kotlin
repositories {
    maven {
        url = uri("https://maven.pcreators.pl/releases")
        credentials {
            username = System.getenv("REPO_USER") ?: "guest"
            password = System.getenv("REPO_PASS") ?: "guest"
        }
    }
    mavenCentral()
}
```

If you're using a -SNAPSHOT version, change the URL to:

```kotlin
url = uri("https://maven.pcreators.pl/snapshots")
```

## ✅ 2. Add the Dependency
In the dependencies block, add:

```kotlin
dependencies {
    implementation("com.serweron:serweronlib:1.0.0")
}
```
For snapshot builds:

```kotlin
dependencies {
    implementation("com.serweron:serweronlib:1.0.0-SNAPSHOT")
}
```