package com.roy.template.common


open class BasePresenter<V : MvpView> : MvpPresenter<V> {

    var mvpView: V? = null
        private set

    override fun onAttach(mvpView: V) {
        this.mvpView = mvpView
    }


}