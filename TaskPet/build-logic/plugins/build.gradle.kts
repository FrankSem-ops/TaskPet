import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "com.wood.taskpet.buildlogic"

// Configure the build-logic plugins to target JDK 17
// This matches the JDK used to build the project, and is not related to what is running on device.
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplicationPlugin") {
            id = "taskpet.android.application"
            implementationClass = "AndroidApplicationPlugin"
        }
        register("androidLibraryPlugin") {
            id = "taskpet.android.library"
            implementationClass = "AndroidLibraryPlugin"
        }
        register("androidLibraryBindingPlugin") {
            id = "taskpet.android.library.binding"
            implementationClass = "AndroidLibraryBindingPlugin"
        }
        register("androidLibraryRouterPlugin") {
            id = "taskpet.android.library.router"
            implementationClass = "AndroidLibraryRouterPlugin"
        }
        register("androidLibraryCorePlugin") {
            id = "taskpet.android.library.core"
            implementationClass = "AndroidLibraryCorePlugin"
        }
        register("androidLibraryFeaturePlugin") {
            id = "taskpet.android.library.feature"
            implementationClass = "AndroidLibraryFeaturePlugin"
        }
//        register("androidLintChecksPlugin") {
//            id = "taskpet.android.lint.checks"
//            implementationClass = "AndroidLintPlugin"
//        }
    }
}