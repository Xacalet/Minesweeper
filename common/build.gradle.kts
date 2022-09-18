import org.jetbrains.compose.compose

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
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material)
                api(compose.materialIconsExtended)
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
        val androidTest by getting {
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
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }

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
