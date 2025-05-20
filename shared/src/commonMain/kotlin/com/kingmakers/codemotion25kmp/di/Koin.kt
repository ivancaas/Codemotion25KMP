package com.kingmakers.codemotion25kmp.di

import com.kingmakers.codemotion25kmp.data.api.ExampleApi
import com.kingmakers.codemotion25kmp.data.dataModule
import com.kingmakers.codemotion25kmp.data.repositories.ExampleRepositoryImpl
import com.kingmakers.codemotion25kmp.domain.repositories.ExampleRepository
import com.kingmakers.codemotion25kmp.presentation.ExampleViewModel
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

internal fun initKoin(
    platformModule: Module,
    modules: List<Module> = emptyList(),
    appDeclaration: KoinAppDeclaration = {},
): KoinApplication {
    return startKoin {
        appDeclaration()
        val commonModule = module {
            single<ExampleRepository> { ExampleRepositoryImpl(get()) }
            single<ExampleViewModel> { ExampleViewModel(get()) }
        }
        if (modules.isNotEmpty())
            modules(modules)
        modules(commonModule, platformModule, dataModule())
    }
}