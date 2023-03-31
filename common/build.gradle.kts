@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    kotlin("multiplatform")
    alias(libs.plugins.composeMultiplatform)
    id("com.android.library")
}

group = "com.xacalet.minesweeper.common"
version = "1.0"

kotlin {
    android()
    jvm("desktop")
    ios()
    js(IR) {
        browser()
        binaries.executable()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material)
                api(compose.ui)
                implementation(libs.kotlin.coroutinesCore)
            }
        }
        val commonTest by getting
        val androidMain by getting {
            dependencies {
                api(libs.androidx.appcompat)
                api(libs.androidx.core)
                api(compose.uiTooling)
                api(compose.preview)
            }
        }
        val androidUnitTest by getting {
            dependencies {
                implementation(libs.junit)
            }
        }
        val desktopMain by getting {
            dependencies {
                api(libs.kotlin.coroutinesSwing)
                api(compose.uiTooling)
                api(compose.preview)
            }
        }
        val desktopTest by getting
        val iosMain by getting
        val iosTest by getting
        val jsMain by getting
        val jsTest by getting

        all {
            languageSettings.optIn("kotlin.RequiresOptIn")
        }
    }
}

android {
    compileSdk = libs.versions.compileSdk.get().toInt()
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
    }
    namespace = "com.xacalet.minesweeper.common"
}
