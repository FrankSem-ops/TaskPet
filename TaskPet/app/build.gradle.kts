plugins {
    alias(libs.plugins.taskpet.android.application)
    alias(libs.plugins.taskpet.android.library.binding)
}

android {
    namespace = "com.wood.taskpet"

    defaultConfig {
        applicationId = "com.wood.taskpet"
        versionCode = 260
        versionName = "5.1.0"
    }
}

dependencies {
    implementation(libs.androidx.compat)
}
