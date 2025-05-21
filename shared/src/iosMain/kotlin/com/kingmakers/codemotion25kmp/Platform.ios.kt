package com.kingmakers.codemotion25kmp

import com.kingmakers.codemotion25kmp.presentation.ExampleViewModel
import com.rickclephas.kmp.observableviewmodel.ViewModel
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.darwin.Darwin
import io.ktor.client.engine.darwin.DarwinClientEngineConfig
import org.koin.core.component.KoinComponent
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

inline fun <reified T : ViewModel> KoinComponent.getViewModel() = getKoin().get<T>()

@Suppress("unused") // Called from Swift
class ViewModels : KoinComponent {
    fun getExampleViewModel(): ExampleViewModel = getViewModel()
}


private fun HttpClientConfig<DarwinClientEngineConfig>.realConfigurePlatform() {
    engine {
        configureRequest {
            setAllowsCellularAccess(true)
        }
    }
}

