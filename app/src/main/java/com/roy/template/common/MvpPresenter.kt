package com.roy.template.common

interface MvpPresenter<in V : MvpView> {

    fun onAttach(mvpView: V)
}