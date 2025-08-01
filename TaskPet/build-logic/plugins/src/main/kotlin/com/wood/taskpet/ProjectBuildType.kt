package com.wood.taskpet

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Project

/**
 *    author: xieyy@anjiu-tech.com
 *    time  : 2024/2/23
 *    desc  : 配置项目的构建类型
 */

enum class TaskPetBuildType(val type: String) {
    DEBUG("debug"),
    STAGING("staging"),
    PREVIEW("preview"),
    RELEASE("release");
}

internal fun ApplicationExtension.configureBuildType(
    project: Project
) {
    buildTypes {
        TaskPetBuildType.values().onEach {
            maybeCreate(it.type).apply {
                // 是否开启混淆及压缩，仅测试环境不开启
                isMinifyEnabled = it != TaskPetBuildType.DEBUG
                // 是否启用多Dex
                multiDexEnabled = true
                // 配置签名信息
                signingConfig = signingConfigs.findByName("yiyuan")
                // 配置自定义信息
                configureManifestPlaceholder(project.taskpetProperties(it))
                // 应用模块，使用proguard-rules.pro作为混淆配置文件
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
        }
    }
}

internal fun LibraryExtension.configureBuildType() {
    buildTypes {
        TaskPetBuildType.values().onEach {
            maybeCreate(it.type).apply {
                // 子模块，使用consumer-rules.pro作为混淆配置文件
                consumerProguardFile("consumer-rules.pro")
            }
        }
    }
}