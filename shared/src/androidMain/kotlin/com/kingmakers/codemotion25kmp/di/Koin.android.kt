package com.kingmakers.codemotion25kmp.di

import com.kingmakers.codemotion25kmp.data.api.ExampleApi
import com.kingmakers.codemotion25kmp.domain.repositories.ExampleRepository
import com.kingmakers.codemotion25kmp.presentation.ExampleViewModel
import org.koin.core.KoinApplication
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoinAndroid(
    modules: List<Module>,
    appDeclaration: KoinAppDeclaration = {}
): KoinApplication {
    val platformModule = module {}
    return initKoin(
        platformModule,
        modules,
        appDeclaration,
    )
}