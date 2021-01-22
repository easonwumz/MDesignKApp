package com.wu.corelib.base

import androidx.annotation.CallSuper
import com.wu.corelib.utils.XLog

abstract class BaseModel {

    abstract fun onCreate()

    @CallSuper
    fun onDestroy() {
        XLog.e("onDestroy", "model:${javaClass.simpleName}")
    }
}