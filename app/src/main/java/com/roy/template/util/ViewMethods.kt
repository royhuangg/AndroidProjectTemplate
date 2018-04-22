package com.roy.template.util

import android.annotation.SuppressLint
import android.content.res.Resources
import android.os.Build
import android.support.annotation.IntRange
import android.support.annotation.Nullable
import android.support.annotation.StringRes
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.ViewTreeObserver
import com.jakewharton.rxbinding2.view.RxView
import java.util.concurrent.TimeUnit


/**
 * Created by Yalan Ding on 2017/6/12.
 */
fun Float.pxToDp(): Float {
    val densityDpi = Resources.getSystem().displayMetrics.densityDpi.toFloat()
    return this / (densityDpi / 160f)
}

fun Float.pxToDpInt(): Int {
    val densityDpi = Resources.getSystem().displayMetrics.densityDpi.toFloat()
    return (this / (densityDpi / 160f)).toInt()
}

fun Float.dpToPx(): Int {
    val density = Resources.getSystem().displayMetrics.density
    return Math.round(this * density)
}

fun AppCompatActivity.setupToolbarWithTextTitle(@Nullable toolbar: Toolbar?, titleString: String?, showHome: Boolean = true, body: (() -> Unit)? = null) {
    this.setSupportActionBar(toolbar)
    if (titleString != null)
        this.supportActionBar?.title = titleString
    this.supportActionBar?.setDisplayHomeAsUpEnabled(showHome)
    this.supportActionBar?.setDisplayShowHomeEnabled(showHome)
    if (body != null)
        toolbar?.setNavigationOnClickListener { body.invoke() }
}

fun AppCompatActivity.setupToolbarWithTextTitle(@Nullable toolbar: Toolbar?, titleString: String?, body: (() -> Unit)? = null) {
    this.setSupportActionBar(toolbar)
    if (titleString != null)
        this.supportActionBar?.title = titleString
    this.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    this.supportActionBar?.setDisplayShowHomeEnabled(true)
    if (body != null)
        toolbar?.setNavigationOnClickListener { body.invoke() }
}

fun AppCompatActivity.setupToolbar(@Nullable toolbar: Toolbar?, @StringRes title: Int, showHome: Boolean = true, body: (() -> Unit)? = null) {
    this.setSupportActionBar(toolbar)

    this.supportActionBar?.setTitle(title)
    this.supportActionBar?.setDisplayHomeAsUpEnabled(showHome)
    this.supportActionBar?.setDisplayShowHomeEnabled(showHome)
    if (body != null)
        toolbar?.setNavigationOnClickListener { body.invoke() }
}

fun AppCompatActivity.setupToolbar(@Nullable toolbar: Toolbar?, @StringRes title: Int, body: (() -> Unit)? = null) {
    this.setSupportActionBar(toolbar)
    this.supportActionBar?.setTitle(title)
    this.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    this.supportActionBar?.setDisplayShowHomeEnabled(true)
    if (body != null)
        toolbar?.setNavigationOnClickListener { body.invoke() }
}


fun View.onGlobalLayout(body: () -> Unit) {
    val vto = viewTreeObserver
    if (vto.isAlive) {
        vto.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            @Suppress("DEPRECATION")
            @SuppressLint("ObsoleteSdkInt")
            override fun onGlobalLayout() {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                    viewTreeObserver.removeGlobalOnLayoutListener(this)
                } else {
                    viewTreeObserver.removeOnGlobalLayoutListener(this)
                }
                body.invoke()
            }
        })
    }
}

fun View.onSafeClick(@IntRange(from = 1) seconds: Long = 2, onNext: (View) -> Unit) {
    RxView.clicks(this)
            .throttleFirst(seconds, TimeUnit.SECONDS)
            .subscribe({
                onNext.invoke(this)
            })
}


//fun View.onSafeClick(provider: com.trello.rxlifecycle2.LifecycleProvider<ActivityEvent>, @IntRange(from = 1) seconds: Long = 2, onNext: (View) -> Unit) {
//    RxView.clicks(this)
//            .compose(provider.bindUntilEvent(ActivityEvent.DESTROY))
//            .throttleFirst(seconds, TimeUnit.SECONDS)
////            .doFinally {  "${this} was doFinally.".loge() }
////            .doOnComplete { "${this} was doOnComplete.".loge()  }
////            .doOnNext { "${this} was doOnNext.".loge()  }
////            .doOnDispose { "${this} was disposed.".loge() }
//            .subscribe({
//                onNext.invoke(this)
//            })
//}
//
//fun View.onFragmentSafeClick(provider: com.trello.rxlifecycle2.LifecycleProvider<FragmentEvent>, @IntRange(from = 1) seconds: Long = 2, onNext: (View) -> Unit) {
//    RxView.clicks(this)
//            .compose(provider.bindUntilEvent(FragmentEvent.DESTROY))
//            .throttleFirst(seconds, TimeUnit.SECONDS)
//            .subscribe({
//                onNext.invoke(this)
//            })
//}
