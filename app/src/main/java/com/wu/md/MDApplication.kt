package com.wu.md

import android.app.Application
import com.wu.corelib.CoreApplication

class MDApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        CoreApplication.init(applicationContext)
    }
}