package com.wood.taskpet

import com.android.build.api.dsl.ApplicationProductFlavor
import com.android.build.api.dsl.BuildType
import java.util.Properties

/**
 *    author: xieyy@anjiu-tech.com
 *    time  : 2024/3/8
 *    desc  :
 */

/**
 * 配置跟随BuildType的ManifestPlaceholders
 * 从gradle/config/yiyuan.xx.properties中读取配置信息
 */
fun BuildType.configureManifestPlaceholder(properties: Properties) {
    properties.onEach { (key, value) ->
        manifestPlaceholders[key.toString()] = value.toString()
    }
}

/**
 * 配置跟随Flavor的ManifestPlaceholders
 * 从gradle/config/flavors/xxx.properties中读取配置信息
 */
fun ApplicationProductFlavor.configureManifestPlaceholder(properties: Properties) {
    properties.forEach { (key, value) ->
        manifestPlaceholders[key.toString()] = value.toString()
    }
}
