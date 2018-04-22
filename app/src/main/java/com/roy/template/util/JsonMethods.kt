package com.roy.template.util

import com.google.gson.Gson
import com.google.gson.JsonParser

fun <T> String?.jsonStringToObject(clazz: Class<T>): T? {
    if (null == this) {
        return null
    }
    val gson = Gson()
    return gson.fromJson(this, clazz)
}

fun <T> String?.jsonStringToList(clazz: Class<T>): List<T>? {
    if (null == this) {
        return null
    }
    var list: ArrayList<T> = ArrayList()
    try {
        val gson = Gson()
        var arry = JsonParser().parse(this).asJsonArray
        arry.mapTo(list) { gson.fromJson(it, clazz) }
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return list

}