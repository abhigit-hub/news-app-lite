plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
}

android {
    compileSdk = libs.versions.compileSdk.get().toInt()
    namespace = "com.compose.newsapplite"
    testNamespace = "test.com.compose.newsapplite"

    defaultConfig {
        applicationId = "com.compose.newsapplite"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
        vectorDrawables.useSupportLibrary = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        compileSdkPreview = "UpsideDownCake"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    val lifecycle_version = "2.7.0"
    val nav_version = "2.7.6"

    val room_version = "2.6.1"

    //room

    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")

    // To use Kotlin annotation processing tool (kapt)
    ksp("androidx.room:room-compiler:$room_version")

    // optional - Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:$room_version")

    //Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0-RC2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.0-RC2")




    // Compose - BOM - (Bill of Materials)
    val composeBom = platform(libs.androidx.compose.bom)
    implementation(composeBom)
    androidTestImplementation(composeBom)

    // Tooling - Preview
    debugImplementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.compose.ui.tooling.preview)

    // Compose - Jetpack Core
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.androidx.core.ktx)

    // Compose - UI
    implementation(libs.androidx.compose.ui)

    // Compose - UI - Material 3
    implementation(libs.androidx.compose.material3)

    // Compose - UI - Animation Graphics
    implementation(libs.androidx.compose.animation.graphics)

    // Coil - KT - Image Loading
    implementation(libs.coil.kt.compose)

    // Retrofit (Retrofit, Gson Converter, OkHTTP Logging)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.okhttp.logging)

    // Dagger - Hilt
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    // Accompanist - Permission
    implementation(libs.accompanist.permission)

    // Destinations - Navigation
    implementation(libs.destinations.core)
    ksp(libs.destinations.ksp)
}