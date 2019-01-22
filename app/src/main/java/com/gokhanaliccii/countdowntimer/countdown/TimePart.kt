package com.gokhanaliccii.countdowntimer.countdown

import java.util.concurrent.TimeUnit

data class TimePart(val minute: Long, val second: Long, val millis: Long)

fun toTimePart(millis: Long): TimePart {
    var remainMillis = millis

    val minute = TimeUnit.MILLISECONDS.toMinutes(millis)
    remainMillis -= TimeUnit.MINUTES.toMillis(minute)
    val second = TimeUnit.MILLISECONDS.toSeconds(remainMillis)
    remainMillis -= TimeUnit.SECONDS.toMillis(second)

    return TimePart(minute, second, remainMillis)
}