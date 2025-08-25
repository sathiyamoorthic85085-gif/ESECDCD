plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.dressmonitor"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.dressmonitor"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner" // Added
    }

    buildTypes {
        release {
            isMinifyEnabled = false // Default, adjust as needed
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

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14" // Ensure this is compatible
    }
}

dependencies {
    // App dependencies from your original root build.gradle.kts
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.4")
    implementation("androidx.activity:activity-compose:1.9.1") // Note: you also have 1.9.2 below, choose one
    implementation(platform("androidx.compose:compose-bom:2024.06.00")) // Note: you also have 2024.08.00 below, choose one
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    dependencies {
        implementation("androidx.navigation:navigation-compose:2.7.7")
        implementation("androidx.compose.material3:material3:1.2.1")
    }


    // From your app/build.gradle.kts (cleaned up nesting)
    // Compose BOM (choose one version, 2024.08.00 is newer)
    implementation(platform("androidx.compose:compose-bom:2024.08.00"))

    // Compose UI (already listed above, redundant if versions match)
    // implementation("androidx.compose.ui:ui")
    // implementation("androidx.compose.ui:ui-tooling-preview")
    // implementation("androidx.compose.material3:material3")

    // AndroidX + Lifecycle (already listed above)
    // implementation("androidx.core:core-ktx:1.13.1")
    // implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.4")
    implementation("androidx.activity:activity-compose:1.9.2") // Newer version than 1.9.1

    // Navigation Compose
    implementation("androidx.navigation:navigation-compose:2.8.0")

    // Firebase (BOM manages versions)
    implementation(platform("com.google.firebase:firebase-bom:33.2.0"))
    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.firebase:firebase-firestore")
    implementation("com.google.firebase:firebase-analytics")

    // Material Icons Extended
    implementation("androidx.compose.material:material-icons-extended-android:1.6.8")

    // Coil for image loading
    implementation("io.coil-kt:coil-compose:2.7.0")

    // Unit testing (runs on JVM) - from your original root build.gradle.kts
    testImplementation("junit:junit:4.13.2")

    // Android instrumented testing (runs on device/emulator) - from your original root build.gradle.kts
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    // Use the same Compose BOM for tests
    androidTestImplementation(platform("androidx.compose:compose-bom:2024.08.00")) // Ensure consistent BOM version
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")

    // Debug tools - from your original root build.gradle.kts (and app/build.gradle.kts)
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}