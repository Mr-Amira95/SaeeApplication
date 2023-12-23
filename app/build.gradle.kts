plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.smartedge.saee"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.smartedge.saee"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("com.google.firebase:firebase-database:20.3.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation("com.hbb20:ccp:2.7.0")
    implementation("com.makeramen:roundedimageview:2.3.0")
    implementation("com.google.android.gms:play-services-location:21.0.1")
    implementation("com.intuit.sdp:sdp-android:1.1.0")
    implementation("com.github.gcacace:signature-pad:1.3.1")
    implementation("com.github.skydoves:powerspinner:1.2.7")
    implementation("pub.devrel:easypermissions:3.0.0")
    implementation("com.github.bumptech.glide:glide:4.16.0")
    implementation("com.github.yuriy-budiyev:code-scanner:2.3.0")

    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.3.0")

}