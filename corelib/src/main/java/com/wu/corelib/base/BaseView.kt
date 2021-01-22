package com.wu.corelib.base

import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

open class BaseView<V : BaseView<V>> : LifecycleObserver {

    private var mActivity: FragmentActivity? = null
    private var mFragment: Fragment? = null
    private var mLifecycle: Lifecycle? = null

    private var mPresenterMap = mutableMapOf<Class<*>, BasePresenter<V>?>()

    fun BaseView(fragment: Fragment) {
        this.mFragment = fragment
        this.mActivity = fragment.activity!!
        this.mLifecycle = fragment.lifecycle
        addLifecycle(this)
    }

    fun BaseView(activity: FragmentActivity) {
        this.mActivity = activity
        this.mLifecycle = activity.lifecycle
        addLifecycle(this)
    }

    fun BaseView(lifecycle: Lifecycle) {
        this.mLifecycle = lifecycle
        addLifecycle(this)
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : FragmentActivity> getActivity(): T {
        return mActivity as T
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : Fragment> getFragment(): T {
        return mFragment as T
    }

    @Suppress("UNCHECKED_CAST")
    fun addPresenter(presenter: BasePresenter<V>?): V {
        if (presenter == null) return this as V

        mPresenterMap[presenter.javaClass] = presenter
        presenter.bindView(this as V)
        return this
    }

    fun <P : BasePresenter<P>> getPresenter(presenterClass: Class<P>?): BasePresenter<V> {
        if (presenterClass == null) {
            throw IllegalArgumentException("presenterClass is null")
        }

        return mPresenterMap[presenterClass]
            ?: throw IllegalArgumentException("presenter of <${presenterClass.simpleName}> is not added!")
    }

    fun addLifecycle(observer: LifecycleObserver) {
        mLifecycle?.addObserver(observer)
    }

    private fun removeLifecycle(observer: LifecycleObserver) {
        mLifecycle?.removeObserver(observer)
    }

    @CallSuper
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        removeLifecycle(this)
        for (presenter in mPresenterMap.values) {
            presenter?.onDestroy()
        }
        mPresenterMap.clear()
    }
}