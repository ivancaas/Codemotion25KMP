package com.kingmakers.codemotion25kmp

import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.darwin.Darwin
import io.ktor.client.engine.darwin.DarwinClientEngineConfig
import platform.Foundation.HTTPBody
import platform.Foundation.HTTPMethod
import platform.Foundation.setValue
import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()

actual val Engine: HttpClientEngineFactory<HttpClientEngineConfig>
    get() = Darwin

actual fun HttpClientConfig<HttpClientEngineConfig>.configurePlatform() {
    @Suppress("UNCHECKED_CAST")
    (this as HttpClientConfig<DarwinClientEngineConfig>).realConfigurePlatform()
}

private fun HttpClientConfig<DarwinClientEngineConfig>.realConfigurePlatform() {
    engine {
        configureRequest {
            setAllowsCellularAccess(true)
        }

        configureRequest {
            //TODO: find a better way
            if (this.HTTPMethod == "GET") {
                this.HTTPBody = null
                this.setValue(null, forHTTPHeaderField = "Content-Length")
            }
        }
    }
}