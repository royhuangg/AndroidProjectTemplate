package com.roy.template.util

import android.content.Context
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * Created by Yalan Ding on 2017/10/12.
 */
fun <T> Observable<T>.doApiThreadSetting(): Observable<T> = this.subscribeOn(io.reactivex.schedulers.Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

fun <T> Flowable<T>.doApiThreadSetting(): Flowable<T> = this.subscribeOn(io.reactivex.schedulers.Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

