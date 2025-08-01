import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 *    author: xieyy@anjiu-tech.com
 *    time  : 2024/2/27
 *    desc  : 项目模块的Lint检查配置插件
 */
class AndroidLintPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            dependencies {
               // add("lintChecks", project(":repository:lint_checks"))
            }
        }
    }
}