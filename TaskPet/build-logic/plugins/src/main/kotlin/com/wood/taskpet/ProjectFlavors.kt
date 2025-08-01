package com.wood.taskpet

import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Project
import java.io.File

/**
 *    author: xieyy@anjiu-tech.com
 *    time  : 2024/2/29
 *    desc  :
 */

private const val DIMENSION = "yiyuan"

internal fun ApplicationExtension.configureFlavors(
    project: Project
) {
    // 读取渠道配置文件，每个文件对应生成一个渠道
    val flavorFiles = project.flavorPropertiesDir.listFiles()?.toList() ?: emptyList<File>()

    flavorDimensions.add(DIMENSION)
    productFlavors {
        flavorFiles.onEach {
            val properties = project.flavorProperties(it.name)
            val flavorName = it.name.removeSuffix(".properties")

            val applicationId = properties.remove("applicationId")?.toString()
                ?: throw IllegalArgumentException("渠道${flavorName}未配置ApplicationID，请检查：${it.absolutePath}")

            create(flavorName) {
                this.dimension = DIMENSION
                this.applicationId = applicationId

                // 将渠道的配置信息放入manifestPlaceholders
                configureManifestPlaceholder(properties)
            }
        }
    }
}