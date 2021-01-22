package com.wu.corelib.base

import android.annotation.TargetApi
import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.ToastUtils
import com.wu.corelib.R
import kotlinx.android.synthetic.main.layout_top.*

abstract class BaseActivity : AppCompatActivity() {

    private lateinit var mActivity: Activity

    private lateinit var parentLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        this.mActivity = this
        super.onCreate(savedInstanceState)
        ActivityUtils.addActivityLifecycleCallbacks(this, null)
        initContentView(R.layout.layout_top)
        setContentView(getLayoutId())

        initialize(savedInstanceState)
    }

    abstract fun getLayoutId(): Int

    abstract fun initView()

    abstract fun initData(savedInstanceState: Bundle?)

    private fun initContentView(@LayoutRes layoutResId: Int) {
        val viewGroup = findViewById<ViewGroup>(android.R.id.content)
        viewGroup.removeAllViews()
        parentLayout = LinearLayout(this)
        parentLayout.orientation = LinearLayout.VERTICAL
        viewGroup.addView(parentLayout)
        LayoutInflater.from(this).inflate(layoutResId, parentLayout, true)
    }

    override fun setContentView(layoutResID: Int) {
        setStatusBarColor(android.R.color.black)
        setAndroidNativeLightStatusBar(false)

        LayoutInflater.from(this).inflate(layoutResID, parentLayout, true)
    }

    protected fun initialize(savedInstanceState: Bundle?) {
        initTopView()
        initView()
        initData(savedInstanceState)
    }

    /**
     * 修改状态栏颜色，安卓5.0以上
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    open fun setStatusBarColor(color: Int) {
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.statusBarColor = resources.getColor(color, null)
        }
    }

    /**
     * 修改状态栏字体颜色
     *
     * @param dark boolean
     */
    @TargetApi(Build.VERSION_CODES.M)
    protected open fun setAndroidNativeLightStatusBar(dark: Boolean) {
        val decor = window.decorView
        if (dark) {
            decor.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        } else {
            decor.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        }
    }

    protected fun initTopView() {
    }

    fun setTitleText(title: String?) {
        setTitleText(title, false)
    }

    fun setTitleText(title: String?, navigationIcon: Boolean) {
        appBarLayout.visibility = View.VISIBLE
        toolBar.title = title
        if (navigationIcon) toolBar.navigationIcon = null;
    }

    fun setTitleText(@StringRes resId: Int) {
        setTitleText(resId, false)
    }

    fun setTitleText(@StringRes resId: Int, navigationIcon: Boolean) {
        appBarLayout.visibility = View.VISIBLE
        toolBar.title = title
        if (navigationIcon) toolBar.navigationIcon = null;
    }

    fun setLeftClickListener(leftImage: ImageView) {
        leftImage.setOnClickListener { finish() }
    }

    fun showToast(msg: String) {
        ToastUtils.showShort(msg)
    }
}