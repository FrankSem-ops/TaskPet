/*
 * Copyright 2023 The Android Open Source Project
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       https://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package com.wood.taskpet

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType
import java.io.File
import java.io.FileInputStream
import java.util.Properties

// 项目依赖扩展
val Project.libs
    get(): VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

// 项目签名配置信息
val Project.keysStoreProperties
    get(): Properties = readProperties("gradle/assemble/keystore.properties")

// 项目渠道配置文件夹
val Project.flavorPropertiesDir
    get(): File = File(rootDir, "gradle/config/flavors")

// 项目开发环境配置信息
val Project.developProperties
    get(): Properties = readProperties("gradle/assemble/develop.properties", canEmpty = true)

/**
 * 项目配置信息
 */
fun Project.taskpetProperties(buildType: TaskPetBuildType): Properties {
    return if (buildType == TaskPetBuildType.DEBUG || buildType == TaskPetBuildType.STAGING) {
        readProperties("gradle/config/yiyuan.debug.properties")
    } else {
        readProperties("gradle/config/yiyuan.release.properties")
    }
}

/**
 * 渠道配置信息
 */
fun Project.flavorProperties(flavorName: String): Properties {
    return readProperties("gradle/config/flavors/$flavorName")
}

/**
 * 从配置文件中读取配置信息
 */
private fun Project.readProperties(relativePath: String, canEmpty: Boolean = false): Properties {
    val propertiesFile = File(rootDir, relativePath)
    if (propertiesFile.exists()) {
        val properties = Properties()
        properties.load(FileInputStream(propertiesFile))
        return properties
    }

    if (canEmpty) {
        return Properties()
    }

    throw IllegalStateException("无法读取配置信息：$relativePath")
}