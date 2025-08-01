import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import com.wood.taskpet.SdkVersion
import com.wood.taskpet.configureBuildType
import com.wood.taskpet.configureFlavors
import com.wood.taskpet.configureKotlinAndroid
import com.wood.taskpet.configureSigning
import com.wood.taskpet.configureVariantEnable
import com.wood.taskpet.configureVariantName
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

/**
 *    author: xieyy@anjiu-tech.com
 *    time  : 2024/2/23
 *    desc  : 项目的Application插件
 */

class AndroidApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("therouter")
                //apply("yiyuan.android.lint.checks")
            }

            extensions.configure<ApplicationExtension> {
                defaultConfig.targetSdk = SdkVersion.TARGET
                defaultConfig.multiDexEnabled = true
                defaultConfig.testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

                defaultConfig.ndk {
                    abiFilters.add("armeabi-v7a")
                    abiFilters.add("arm64-v8a")
                }

                configureKotlinAndroid(this)
                configureSigning(project)
                configureBuildType(project)
                configureFlavors(project)
            }

            extensions.configure<ApplicationAndroidComponentsExtension> {
                configureVariantEnable(this@with)
                configureVariantName()
            }
        }
    }
}