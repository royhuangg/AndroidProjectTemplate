package com.roy.template.common

import android.content.Context
import android.support.v4.app.Fragment

abstract class BaseFragment : Fragment(), MvpView {

    var baseActivity: BaseActivity? = null
        private set


    override fun onAttach(context: Context?) {
        super.onAttach(context)

        if (context is BaseActivity) {
            val activity = context
            this.baseActivity = activity
//            activity.onFragmentAttached()
        }
    }

    override fun onDetach() {
        super.onDetach()
        baseActivity = null
    }

    override fun showLoading() {
        baseActivity?.showLoading()
    }

    override fun hideLoading() {
        baseActivity?.hideLoading()
    }


}