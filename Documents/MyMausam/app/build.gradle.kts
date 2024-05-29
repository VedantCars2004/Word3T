plugins {
    alias(libs.plugins.androidApplication)
}

android {
    namespace = "com.example.mymausam"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.mymausam"
        minSdk = 24
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
}

dependencies {
    // this is the powerful image downloading and caching library for Android
    implementation("com.squareup.picasso:picasso:2.71828")
    // HTTP library developed by Google for Android
    implementation("com.android.volley:volley:1.2.1")
    // helpful for location based services in Android applications
    implementation("com.google.android.gms:play-services-location:21.2.0")

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}