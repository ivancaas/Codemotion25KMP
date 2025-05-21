package com.kingmakers.codemotion25kmp

import android.content.Context
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase

actual class FirebaseAnalyticsWrapper {
    private lateinit var firebaseAnalytics: FirebaseAnalytics
    
    /**
     * Inicializa Firebase Analytics con el contexto de Android.
     * Debe llamarse al inicio de la aplicación.
     */
    fun init() {
        firebaseAnalytics = Firebase.analytics
    }

    actual fun logEvent(name: String, params: Map<String, Any>?) {
        val bundle = Bundle()
        params?.forEach { (key, value) ->
            when (value) {
                is String -> bundle.putString(key, value)
                is Int -> bundle.putInt(key, value)
                is Long -> bundle.putLong(key, value)
                is Double -> bundle.putDouble(key, value)
                is Boolean -> bundle.putBoolean(key, value)
                else -> bundle.putString(key, value.toString())
            }
        }
        firebaseAnalytics.logEvent(name, bundle)
    }
    
    actual fun setUserProperty(name: String, value: String?) {
        firebaseAnalytics.setUserProperty(name, value)
    }
    
    actual fun setUserId(userId: String?) {
        firebaseAnalytics.setUserId(userId)
    }
}