import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 *    author: xieyy@anjiu-tech.com
 *    time  : 2024/2/27
 *    desc  : 项目的核心子模块配置插件
 */
class AndroidLibraryCorePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("taskpet.android.library")
                apply("taskpet.android.library.binding")
                apply("taskpet.android.library.router")
            }

            dependencies {
                add("api", project(":common"))
            }
        }
    }
}