package com.gokhanaliccii.countdowntimer.countdown.checker

class ExtensibilityBySecondChecker(
    private val increment: Long,
    private val max: Long
) : TimeExtensibility {

    override fun isTimeExtensible(remain: Long): Boolean {
        return remain + increment <= max
    }
}