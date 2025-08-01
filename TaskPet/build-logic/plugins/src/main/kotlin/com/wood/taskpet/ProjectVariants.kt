package com.wood.taskpet

import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import com.android.build.api.variant.ApplicationVariant
import com.android.build.api.variant.impl.VariantOutputImpl
import org.gradle.api.Project
import org.gradle.kotlin.dsl.support.uppercaseFirstChar
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 *    author: xieyy@anjiu-tech.com
 *    time  : 2024/2/23
 *    desc  : 配置项目的变体
 */

internal fun ApplicationAndroidComponentsExtension.configureVariantEnable(project: Project) {
    // 读取开发配置信息
    val properties = project.developProperties
    val enabledFlavors = TaskPetBuildType.values().associate {
        it.type to properties.getProperty("${it.type}.flavors")?.split(",")
    }

    // 配置变体是否开启
    beforeVariants { variant ->
        if (variant.buildType == TaskPetBuildType.RELEASE.type) {
            val enableFlavors = enabledFlavors[variant.buildType] ?: emptyList()
            if (enableFlavors.isNotEmpty()) {
                // 如果配置文件中指定了release构建类型的渠道，则只开启指定的渠道
                variant.enable = enableFlavors.contains(variant.flavorName)
            } else {
                // 未指定渠道，则开启release构建类型的所有渠道
                variant.enable = true
            }
            return@beforeVariants
        }

        // 其它构建类型，根据配置文件决定是否开启
        variant.enable = enabledFlavors[variant.buildType]?.contains(variant.flavorName) == true
    }
}

internal fun ApplicationAndroidComponentsExtension.configureVariantName() {
    onVariants { variant ->
        variant.outputs.filterIsInstance<VariantOutputImpl>().onEach { output ->
            val versionName = output.versionName.get().orEmpty()
            val versionCode = output.versionCode.get() ?: 0
            val flavorName = variant.flavorName.orEmpty()
            val buildType = variant.buildType.orEmpty().uppercaseFirstChar()
            val byteDanceTag = variant.byteDanceTag()
            val currentTime = currentTime()

            // e.g. 4.0.0_v150_0910Debug_新版头条_02231105.apk
            val newFileName =
                "${versionName}_v${versionCode}_${flavorName}${buildType}${byteDanceTag}_${currentTime}.apk"
            output.outputFileName.set(newFileName)
        }
    }
}

private fun ApplicationVariant.byteDanceTag(): String {
    val tag = applicationId.get().replace("com.wood.taskpet", "")
    val oldVersion = listOf("a", "b", "e")
    if (tag.isEmpty() || tag in oldVersion) {
        return ""
    }

    return "_新版头条"
}

private fun currentTime(): String {
    return SimpleDateFormat("MMddHHmm", Locale.CHINA).format(Date())
}