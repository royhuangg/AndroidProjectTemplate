package com.roy.template.util

import android.content.Context
import android.support.v4.content.ContextCompat


fun Int.bindColor(context: Context): Int = ContextCompat.getColor(context, this)

fun Int?.bindColor(context: Context): Int? = if (this != null && this != 0) try {
    ContextCompat.getColor(context, this)
} catch (e: Exception) {
    e.loge()
    null
} else null

fun Int.bindDimenFloat(context: Context?): Float? = context?.resources?.getDimension(this)
fun Int.bindDimenInt(context: Context?): Int? = context?.resources?.getDimensionPixelOffset(this)
fun Int.bindString(context: Context?, vararg args: Any): String = context?.getString(this, *args)
        ?: ""

fun Int.bindString(context: Context?): String = context?.getString(this) ?: ""
fun Int.bindStringArray(context: Context?): Array<String> = context?.resources?.getStringArray(this)
        ?: emptyArray()

fun Int.bindStringMutableList(context: Context?): MutableList<String> = (context?.resources?.getStringArray(this)
        ?: emptyArray<String>()).toMutableList()


fun String.bindStringByResName(context: Context?): String {
    val resId = context?.resources?.getIdentifier(this, "string", context.packageName) ?: 0
    if (resId == 0) {
        return ""
    }

    return resId.bindString(context)
}

fun String.bindDrawableIdByResName(context: Context): Int = context.resources.getIdentifier(this, "drawable", context.packageName)