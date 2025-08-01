package com.wood.taskpet

import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Project
import java.io.File

/**
 *    author: xieyy@anjiu-tech.com
 *    time  : 2024/2/23
 *    desc  : 配置项目的签名
 */

internal fun ApplicationExtension.configureSigning(
    project: Project
) {
    signingConfigs {
        // 创建名为taskpet的签名配置
        create("yiyuan") {
            val properties = project.keysStoreProperties
            storeFile = File(project.rootDir, properties["KEY_LOCATION"].toString())
            storePassword = properties["KEYSTORE_PASS"].toString()
            keyAlias = properties["ALIAS_NAME"].toString()
            keyPassword = properties["ALIAS_PASS"].toString()

            enableV1Signing = true
            enableV2Signing = true
        }
    }
}