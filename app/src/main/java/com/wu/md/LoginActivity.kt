package com.wu.md

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.blankj.utilcode.util.KeyboardUtils
import com.wu.corelib.base.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(), View.OnClickListener {

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun initView() {
        setTitleText("Sign In Music", true)

        btnLogin.setOnClickListener(this)
        etPhone.setText("18352809183")
        etPwd.setText("000000")

        etPwd.addTextChangedListener(watcher)
        textInputPwd.setErrorIconOnClickListener { textInputPwd.error = "密码是：000000" }
    }

    override fun initData(savedInstanceState: Bundle?) {
    }

    private val watcher = object : TextWatcher {

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            textInputPwd.error = ""
        }

        override fun afterTextChanged(s: Editable?) {
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnLogin -> {
                val phone = etPhone.text.toString().toLong()
                val pwd = etPwd.text.toString()
                if (phone == 18352809183 && pwd == "000000") {
                    KeyboardUtils.hideSoftInput(this)
                    startActivity(MainActivity.jump(this, phone))
                    finish()
                } else {
                    textInputPwd.error = "手机号或密码错误"
                }
            }
        }
    }
}