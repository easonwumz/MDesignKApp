package com.wu.md.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.wu.corelib.base.BaseActivity
import com.wu.md.R
import com.wu.md.adapter.NavAdapter
import com.wu.md.entity.NavInfo
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : BaseActivity() {

    private var nameText: TextView? = null
    private var descText: TextView? = null

    private var exitTime = 0L

    companion object {
        fun jump(context: Context, phone: String) {
            val starter = Intent(context, MainActivity::class.java)
            starter.putExtra("phone", phone)
            context.startActivity(starter)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        val list = mutableListOf<NavInfo>()
        list.add(NavInfo(2, "消息中心", R.drawable.ic_message))
        list.add(NavInfo(2, "云贝中心", R.drawable.ic_message))
        list.add(NavInfo(2, "创作者中心", R.drawable.ic_message))

        val adapter = NavAdapter(list)
        adapter.animationEnable = true
        adapter.addHeaderView(getHeaderView())
        recyclerView.adapter = adapter;

        ivMenu.setOnClickListener { drawerLayout.openDrawer(GravityCompat.START) }
    }

    private fun getHeaderView(): View {
        val headerView = LayoutInflater.from(this).inflate(R.layout.nav_header_main, null, false)
        nameText = headerView.findViewById(R.id.tvName)
        descText = headerView.findViewById(R.id.tvDesc)
        return headerView
    }

    override fun initData(savedInstanceState: Bundle?) {
        val mobile = intent.getStringExtra("phone")
        nameText?.text = "Wumz"
        descText?.text = mobile
    }


    override fun onBackPressed() {
        val currentTime = System.currentTimeMillis()
        if ((currentTime - exitTime) < 2000) {
            super.onBackPressed()
        } else {
            showToast("再按一次退出程序")
            exitTime = currentTime
        }
    }
}