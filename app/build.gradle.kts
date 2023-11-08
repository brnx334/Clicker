
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
}


android {
    namespace = "com.brnx.clicker"
    compileSdk = 34


    defaultConfig {
        applicationId = "com.brnx.clicker"
        minSdk = 24
        targetSdk = 33
        versionCode = 16
        versionName = "1.7"

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

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.2"
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
    defaultConfig {
        vectorDrawables.useSupportLibrary = true
    }



}
dependencies {
    val nav_version = "2.7.4"
    val room_version = "2.6.0"
    val composeBom = platform("androidx.compose:compose-bom:2023.10.00")

    implementation("androidx.datastore:datastore-core-android:1.1.0-alpha05")
    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")
    implementation(composeBom)
    androidTestImplementation(composeBom)
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation("androidx.compose.ui:ui-tooling-preview")
    debugImplementation("androidx.compose.ui:ui-tooling")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material3:material3")
    implementation ("androidx.activity:activity-compose:1.8.0")
    implementation ("androidx.room:room-ktx:2.6.0")

    //to changing systemUI colors
    implementation ("com.google.accompanist:accompanist-systemuicontroller:0.27.0")
    ksp("androidx.room:room-compiler:$room_version")
    //toasty
    implementation("com.github.GrenderG:Toasty:1.5.2")
    //navigation
    implementation("androidx.navigation:navigation-compose:$nav_version")
    implementation("com.google.devtools.ksp:symbol-processing-api:1.9.10-1.0.13")
    //Google services
    implementation ("com.google.android.gms:play-services-games-v2:+")


}
