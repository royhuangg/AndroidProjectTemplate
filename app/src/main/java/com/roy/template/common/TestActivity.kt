package com.roy.template.common

import android.os.Bundle
import com.roy.template.R

class TestActivity : BaseActivity(), TestMvpView {

    var mPresenter = TestPresenter<TestActivity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_aml_info)
        mPresenter.onAttach(this)
    }

    override fun testFun() {
//        tvIndustry.text = "dddddd"
    }
}