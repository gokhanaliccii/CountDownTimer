package com.gokhanaliccii.countdowntimer.countdown.checker

interface TimeExtensibility {

    fun isTimeExtensible(remain: Long): Boolean

    fun isReachedToMaxLimit(remain: Long): Boolean
}