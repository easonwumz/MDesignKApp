package com.wu.corelib.base

import androidx.annotation.CallSuper

abstract class BasePresenter<V : BaseView<*>> {

    private var mView: V? = null

    private var mModelMap = mutableMapOf<Class<*>, BaseModel>()
    private var isAlive = true

    abstract fun onBindView()

    fun bindView(view: V) {
        this.mView = view
        onBindView()
    }

    fun getView(): V? {
        return mView
    }

    fun isAlive(): Boolean {
        return isAlive
    }

    @Suppress("UNCHECKED_CAST")
    fun <M : BaseModel> getModel(modelClass: Class<M>): M? {
        val baseModel = mModelMap[modelClass]
        if (baseModel != null) {
            return baseModel as M
        }

        try {
            val model = modelClass.newInstance()
            mModelMap[modelClass] = model
            model.onCreate()
            return model
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: InstantiationException) {
            e.printStackTrace()
        }
        return null
    }

    @CallSuper
    fun onDestroy() {
        this.isAlive = false
        for (model in mModelMap.values) {
            model.onDestroy()
        }
        mModelMap.clear()
    }
}