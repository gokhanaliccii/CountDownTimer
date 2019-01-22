package com.gokhanaliccii.countdowntimer.countdown.formatter

import java.util.concurrent.TimeUnit

// This class holds minute, second and millisecond
data class TimePart(val minute: Long, val second: Long, val millis: Long)

// This function converts millisecond to minute, second and millisecond
fun toTimePart(millis: Long): TimePart {
    var remainMillis = millis

    val minute = TimeUnit.MILLISECONDS.toMinutes(millis)
    remainMillis -= TimeUnit.MINUTES.toMillis(minute)
    val second = TimeUnit.MILLISECONDS.toSeconds(remainMillis)
    remainMillis -= TimeUnit.SECONDS.toMillis(second)

    return TimePart(
        minute,
        second,
        remainMillis
    )
}