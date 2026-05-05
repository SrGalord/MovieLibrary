plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
    id("androidx.navigation.safeargs.kotlin")
}

android {

    namespace = "com.example.ordoezjaddys"

    compileSdk = 36

    defaultConfig {

        applicationId = "com.example.ordoezjaddys"

        minSdk = 24
        targetSdk = 36

        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner =
            "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {

        release {

            isMinifyEnabled = false

            proguardFiles(
                getDefaultProguardFile(
                    "proguard-android-optimize.txt"
                ),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {

        sourceCompatibility =
            JavaVersion.VERSION_11

        targetCompatibility =
            JavaVersion.VERSION_11
    }

    kotlinOptions {

        jvmTarget = "11"
    }

    buildFeatures {

        viewBinding = true
    }
}

kapt {

    correctErrorTypes = true
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    testImplementation(libs.junit)

    androidTestImplementation(libs.androidx.junit)

    androidTestImplementation(
        libs.androidx.espresso.core
    )

    // ROOM
    implementation(
        "androidx.room:room-runtime:2.7.0"
    )

    implementation(
        "androidx.room:room-ktx:2.7.0"
    )

    kapt(
        "androidx.room:room-compiler:2.7.0"
    )

    // VIEWMODEL Y LIVEDATA
    implementation(
        "androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.3"
    )

    implementation(
        "androidx.lifecycle:lifecycle-livedata-ktx:2.8.3"
    )

    // NAVIGATION
    implementation(
        "androidx.navigation:navigation-fragment-ktx:2.7.7"
    )

    implementation(
        "androidx.navigation:navigation-ui-ktx:2.7.7"
    )

    // RECYCLERVIEW
    implementation(
        "androidx.recyclerview:recyclerview:1.3.2"
    )

    // COROUTINES
    implementation(
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1"
    )
}