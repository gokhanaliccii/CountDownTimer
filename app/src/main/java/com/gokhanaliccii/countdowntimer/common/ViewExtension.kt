package com.gokhanaliccii.countdowntimer.common

import android.view.View

fun <T : View> T.hide() {
    visibility = View.GONE
}

fun <T : View> T.display() {
    visibility = View.VISIBLE
}