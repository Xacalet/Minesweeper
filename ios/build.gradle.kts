@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    kotlin("multiplatform")
    alias(libs.plugins.composeMultiplatform)
}

group = "com.xacalet.minesweeper.ios"

kotlin {
    iosX64("uikitX64") {
        binaries {
            executable() {
                entryPoint = "main"
                freeCompilerArgs += listOf(
                    "-linker-option", "-framework", "-linker-option", "Metal",
                    "-linker-option", "-framework", "-linker-option", "CoreText",
                    "-linker-option", "-framework", "-linker-option", "CoreGraphics"
                )
            }
        }
    }
    iosArm64("uikitArm64") {
        binaries {
            executable() {
                entryPoint = "main"
                freeCompilerArgs += listOf(
                    "-linker-option", "-framework", "-linker-option", "Metal",
                    "-linker-option", "-framework", "-linker-option", "CoreText",
                    "-linker-option", "-framework", "-linker-option", "CoreGraphics"
                )
            }
        }
    }

    sourceSets {
        val uikitMain by creating {
            dependencies {
                implementation(projects.common)
            }
        }
        val uikitX64Main by getting {
            dependsOn(uikitMain)
        }
        val uikitArm64Main by getting {
            dependsOn(uikitMain)
        }

        all {
            languageSettings.optIn("kotlin.RequiresOptIn")
        }
    }
}

compose.experimental {
    uikit.application {
        bundleIdPrefix = "com.xacalet"
        projectName = "Minesweeper"
        deployConfigurations {
            simulator("IPhone8") {
                //Usage: ./gradlew iosDeployIPhone8Debug
                device = org.jetbrains.compose.experimental.dsl.IOSDevices.IPHONE_8
            }
            simulator("IPad") {
                //Usage: ./gradlew iosDeployIPadDebug
                device = org.jetbrains.compose.experimental.dsl.IOSDevices.IPAD_MINI_6th_Gen
            }
            connectedDevice("Device") {
                //First need specify your teamId here, or in local.properties (compose.ios.teamId=***)
                //teamId="***"
                //Usage: ./gradlew iosDeployDeviceRelease
            }
        }
    }
}
