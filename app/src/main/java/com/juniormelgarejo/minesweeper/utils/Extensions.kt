package com.juniormelgarejo.minesweeper.utils

import android.content.Context
import android.content.res.ColorStateList
import android.view.View
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun View.showValue(show: Boolean) {
    backgroundTintList =
        ColorStateList.valueOf(
            context.colorCompat(
                if (show) android.R.color.transparent else android.R.color.darker_gray
            )
        )
}

fun View.setVisible(visible: Boolean) {
    visibility = if(visible) View.VISIBLE else View.GONE
}

fun Context.colorCompat(@ColorRes colorId: Int) = ContextCompat.getColor(this, colorId)


fun consume(handler: () -> Unit): Boolean {
    handler.invoke()
    return true
}

fun <T> LiveData<T>.observeAction(owner: LifecycleOwner, observer: (T) -> Unit) {
    observe(owner, Observer { it?.let { observer(it) } })
}


fun <T> LiveData<Event<T>>.observeEvent(owner: LifecycleOwner, observer: (T) -> Unit) {
    observe(owner, EventObserver(observer))
}

fun safeIf(boolean: Boolean?, handler: () -> Unit): Boolean? {
    return if (boolean == true) consume(handler) else boolean
}

infix fun Boolean?.safeElse(handler: () -> Unit) {
    if (this != true) handler()
}