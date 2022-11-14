@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    kotlin("multiplatform")
    alias(libs.plugins.composeMultiplatform)
}

group = "com.xacalet.minesweeper.web"

kotlin {
    js(IR) {
        browser()
        binaries.executable()
    }

    sourceSets {
        val jsMain by getting {
            dependencies {
                implementation(projects.common)
                implementation(compose.web.core)
                implementation(compose.runtime)
            }
        }
        val jsTest by getting

        all {
            languageSettings.optIn("kotlin.RequiresOptIn")
        }
    }
}

compose.experimental {
    web.application {}
}

// Workaround for a bug in jsRun invocation - https://youtrack.jetbrains.com/issue/KT-48273
afterEvaluate {
    rootProject.extensions.configure<org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootExtension> {
        versions.webpackDevServer.version = "4.11.0"
        versions.webpackCli.version = "4.10.0"
        nodeVersion = "16.0.0"
    }
}
