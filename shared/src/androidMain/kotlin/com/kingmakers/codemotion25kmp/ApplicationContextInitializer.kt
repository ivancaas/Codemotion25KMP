package com.kingmakers.codemotion25kmp

import android.content.Context
import androidx.startup.Initializer

internal lateinit var applicationContext: Context

internal class ApplicationContextInitializer : Initializer<Context> {
    override fun create(context: Context): Context =
        context.also {
            applicationContext = it
        }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}
