package com.gokhanaliccii.countdowntimer.countdown.checker

interface TimeExtensibilityChecker {

    fun isTimeExtensible(remain: Long): Boolean

    fun isReachedToMaxLimit(remain: Long): Boolean
}