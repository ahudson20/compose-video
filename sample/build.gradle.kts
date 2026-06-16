import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("com.android.application")
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "io.sanghun.compose.video.sample"
    compileSdk = 36

    defaultConfig {
        applicationId = "io.sanghun.compose.video.sample"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables.useSupportLibrary = true
    }

    signingConfigs {
        getByName("debug") {
            storeFile = rootProject.file("debug.keystore")
            storePassword = "android"
            keyAlias = "androiddebugkey"
            keyPassword = "android"
        }
    }

    buildTypes {
        debug {
            signingConfig = signingConfigs.getByName("debug")
        }
        create("benchmark") {
            isDebuggable = false
            signingConfig = signingConfigs.getByName("debug")
            matchingFallbacks.add("release")
        }
        release {
            signingConfig = signingConfigs.getByName("debug")
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        compose = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_1_8)
    }
}

dependencies {
    implementation(project(":compose-video"))

    implementation(libs.bundles.androidx)
    implementation(libs.bundles.compose)
    implementation(libs.bundles.media3)
    implementation(libs.material3)
    testImplementation(libs.junit)
    debugImplementation(libs.bundles.compose.debugOnly)

    implementation(libs.media3.dash)
    implementation(libs.media3.hls)

    androidTestImplementation(libs.bundles.androidTest)
}
