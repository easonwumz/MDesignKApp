package com.wu.corelib

import android.content.Context
import com.wu.corelib.utils.PreferencesHelper

class CoreApplication {

    companion object {
        fun init(context: Context) {
            PreferencesHelper.init(context)
        }
    }
}