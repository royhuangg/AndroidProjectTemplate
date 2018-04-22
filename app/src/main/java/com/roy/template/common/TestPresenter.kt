package com.roy.template.common

class TestPresenter<V : TestMvpView> : BasePresenter<V>(), TestMvpPresenter<V> {


    override fun onAttach(mvpView: V) {
        super.onAttach(mvpView)
        mvpView?.testFun()

    }

    override fun testPresenter() {

    }
}