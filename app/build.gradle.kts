plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.dicoding.mymvvm"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.dicoding.mymvvm"
        minSdk = 24
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    configurations.all {
        exclude(group = "com.intellij", module = "annotations")
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Room Database
    implementation(libs.androidx.room)

    // Room Database
    implementation(libs.androidx.room) // Room KTX
    ksp(libs.androidx.room.compiler) // Room Compiler (via KSP)

    // Lifecycle Libraries
    implementation(libs.androidx.lifecycle.runtime.ktx) // Lifecycle Runtime KTX
    implementation(libs.androidx.lifecycle.viewmodel.ktx) // Lifecycle ViewModel KTX
    implementation(libs.androidx.lifecycle.livedata.ktx) // Lifecycle LiveData KTX
}