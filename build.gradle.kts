plugins {
    id("com.android.application") version "9.1.1" apply false
    id("com.android.library") version "9.1.1" apply false
//    id("org.jetbrains.kotlin.android") version "2.3.21" apply false
    id("org.jetbrains.kotlin.plugin.compose") version "2.3.21" apply false

    id("com.diffplug.spotless") version "8.4.0"
    id("io.github.gradle-nexus.publish-plugin") version "2.0.0"
    id("org.jetbrains.dokka") version "2.2.0"
}

dokka {
    dokkaPublications.html {
        outputDirectory.set(rootProject.file("docs/api"))
        failOnWarning.set(true)
    }
}

apply(from = "${rootDir}/scripts/generate-dokka.gradle")

subprojects {
    apply(plugin = "com.diffplug.spotless")

    spotless {
        kotlin {
            target("**/*.kt")
            targetExclude(layout.buildDirectory.get().asFile.absolutePath + "/**/*.kt")

            ktlint("0.48.2")
            licenseHeaderFile(rootProject.file("spotless/copyright.kt"))
        }
    }
}
