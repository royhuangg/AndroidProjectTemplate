package com.roy.template.common


interface TestMvpView : MvpView {
    fun testFun()


}

interface TestMvpPresenter<in V : MvpView> : MvpPresenter<V> {
    fun testPresenter()

}