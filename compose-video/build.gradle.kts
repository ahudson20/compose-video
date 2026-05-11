import org.jetbrains.kotlin.gradle.dsl.JvmTarget

apply(from = "${rootDir}/publish.gradle")
apply(from = "${rootDir}/scripts/publish-root.gradle")
apply(from = "${rootDir}/scripts/publish-module.gradle")

plugins {
    id("com.android.library")
//    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
    id("me.tylerbwong.gradle.metalava") version "0.5.0"
    id("org.jetbrains.dokka")
}

metalava {
    filename.set("api/current.api")
    reportLintsAsErrors.set(true)
}

android {
    namespace = "io.sanghun.compose.video"
    compileSdk = 36

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildFeatures {
        compose = true
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
//    kotlinOptions {
//        jvmTarget = "1.8"
//    }
//    kotlin {
//        compilerOptions {
//            jvmTarget.set(JvmTarget.JVM_1_8)
//        }
//    }
    lint {
        targetSdk = 36
    }
    testOptions {
        targetSdk = 36
    }
}

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_1_8)
    }
}

afterEvaluate {
    tasks.named("dokkaHtmlPartial") {

    }
}

dependencies {
    implementation(libs.androidx.core)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.media)
    implementation(libs.bundles.compose)
    implementation(libs.bundles.media3)
    implementation(libs.material2)

    debugImplementation(libs.bundles.compose.debugOnly)

    testImplementation(libs.junit)
    androidTestImplementation(libs.bundles.androidTest)
}
