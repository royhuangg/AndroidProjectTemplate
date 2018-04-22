package com.roy.template.util

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Created by Roy on 2018/2/22.
 */
@Suppress("UNCHECKED_CAST")
fun <T> Activity.lazyExtra(name: String, defaultValue: T): Lazy<T> {
    return lazy {
        when (defaultValue) {
            is String -> intent.getStringExtra(name) as T ?: defaultValue
            is Int -> intent.getIntExtra(name, defaultValue) as T
            is Boolean -> intent.getBooleanExtra(name, defaultValue) as T
            else -> throw RuntimeException()
        }

    }
}

@Suppress("UNCHECKED_CAST")
fun <T> Fragment.arg(name: String, defaultValue: T) = object : ReadWriteProperty<Any, T> {
    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        if (arguments == null) {
            return defaultValue
        }

        when (defaultValue) {
            is String -> return arguments?.getString(name, defaultValue) as T
            is Int -> return arguments?.getInt(name, defaultValue) as T
            is Boolean -> return arguments?.getBoolean(name, defaultValue) as T
            is Long -> return arguments?.getLong(name, defaultValue) as T
            else -> throw RuntimeException()
        }
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        if (arguments == null) {
            arguments = Bundle()
        }

        when (value) {
            is String -> arguments?.putString(name, value)
            is Int -> arguments?.putInt(name, value)
            is Boolean -> arguments?.putBoolean(name, value)
            is Long -> arguments?.putLong(name, value)
            else -> throw RuntimeException()
        }
    }
}