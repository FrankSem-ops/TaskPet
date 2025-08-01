import com.android.build.gradle.LibraryExtension
import com.wood.taskpet.SdkVersion
import com.wood.taskpet.configureBuildType
import com.wood.taskpet.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

/**
 *    author: xieyy@anjiu-tech.com
 *    time  : 2024/2/26
 *    desc  : 项目的Library插件
 */
class AndroidLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
              //  apply("yiyuan.android.lint.checks")
            }

            extensions.configure<LibraryExtension> {
                defaultConfig.targetSdk = SdkVersion.TARGET

                configureKotlinAndroid(this)
                configureBuildType()
            }
        }
    }
}