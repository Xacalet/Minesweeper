import org.jetbrains.compose.desktop.application.dsl.TargetFormat

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("java")
    kotlin("multiplatform")
    alias(libs.plugins.composeMultiplatform)
}

group = "com.xacalet.minesweeper"

kotlin {
    jvm {
        compilations.all {
            kotlinOptions {
                jvmToolchain(17)
            }
        }
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(projects.common)
                implementation(compose.desktop.currentOs)
            }
        }
        val jvmTest by getting
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "jvm"
            packageVersion = "1.0.0"
        }
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}
