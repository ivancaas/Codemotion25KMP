package com.kingmakers.codemotion25kmp
expect class FirebaseAnalyticsWrapper() {
    fun logEvent(name: String, params: Map<String, Any>? = null)
    fun setUserProperty(name: String, value: String?)
    fun setUserId(userId: String?)
}