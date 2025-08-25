// build.gradle.kts (Module level - e.g., app/build.gradle.kts)
plugins {
    id("com.android.application") version "8.5.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.24" apply false
    id("com.google.gms.google-services") version "4.4.2" apply false
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}


android {
    namespace = "com.example.esec_dcd" // Make sure this is your actual namespace
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.esec_dcd" // And your actual application ID
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true // Enable Compose if you are using it
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.9.0" // Use the version from your libs.versions.toml or a compatible one
    }
    packaging { // packagingOptions is now packaging
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.compose.ui:ui:1.6.0")
    implementation("androidx.compose.material3:material3:1.2.0")
    implementation("androidx.compose.ui:ui-tooling-preview:1.6.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.9.3") // Updated to 1.9.3 as per your initial file

    // Navigation components
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.3")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.3")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0") // It was 1.9.0, keeping it unless you intended to update

    // Ensure you have Compose BOM if using multiple Compose libraries
    // implementation(platform("androidx.compose:compose-bom:2023.08.00")) // Example, use your desired BOM version

    debugImplementation("androidx.compose.ui:ui-tooling:1.6.0")
    // testImplementation("junit:junit:4.13.2")
    // androidTestImplementation("androidx.test.ext:junit:1.1.5")
    // androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    // androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    // androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    // debugImplementation("androidx.compose.ui:ui-test-manifest")
}

// REMOVE these as they are not valid Kotlin DSL for build scripts:
// annotation class implementation(val value: String)
// private fun DependencyHandlerScope.debugImplementation(string: String) {}
    