import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 *    author: xieyy@anjiu-tech.com
 *    time  : 2024/2/26
 *    desc  : 项目模块的Binding配置插件
 */

class AndroidLibraryBindingPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {

            extensions.getByType(CommonExtension::class.java).apply {
                viewBinding { enable = true }
            }
        }
    }
}