package com.kingmakers.codemotion25kmp

import cocoapods.FirebaseAnalytics.FIRAnalytics
import platform.Foundation.NSMutableDictionary
import platform.Foundation.NSNumber
import platform.Foundation.numberWithBool
import platform.Foundation.numberWithDouble
import platform.Foundation.numberWithInt
import platform.Foundation.numberWithLong

actual class FirebaseAnalyticsWrapper {
    
    actual fun logEvent(name: String, params: Map<String, Any>?) {
        if (params == null) {
            FIRAnalytics.logEventWithName(name, null)
        } else {
            FIRAnalytics.logEventWithName(name, params as Map<Any?, *>?)
        }
    }
    
    actual fun setUserProperty(name: String, value: String?) {
        FIRAnalytics.setUserPropertyString(value, name)
    }
    
    actual fun setUserId(userId: String?) {
        FIRAnalytics.setUserID(userId)
    }
}
