rootProject.name = "SerweronCore"
include("core", "core-ranks", "lib")



plugins {
    id("io.papermc.paperweight.userdev") version "1.7.7" apply false
    kotlin("jvm") version "2.1.0" apply false
}