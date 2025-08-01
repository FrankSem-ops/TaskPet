import com.wood.taskpet.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 *    author: xieyy@anjiu-tech.com
 *    time  : 2024/2/27
 *    desc  : 项目模块的路由配置插件
 */
class AndroidLibraryRouterPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.google.devtools.ksp")
            }

            dependencies {
                add("ksp", libs.findLibrary("router.apt").get())
            }
        }
    }
}