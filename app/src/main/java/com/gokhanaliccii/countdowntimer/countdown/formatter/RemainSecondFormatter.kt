package com.gokhanaliccii.countdowntimer.countdown.formatter

import java.util.concurrent.TimeUnit


class RemainSecondFormatter {

    fun format(millis: Long): String {
        var totalMillis = millis

        val minute = TimeUnit.MILLISECONDS.toMinutes(millis)
        totalMillis -= TimeUnit.MINUTES.toMillis(minute)
        val second = TimeUnit.MILLISECONDS.toSeconds(totalMillis)
        totalMillis -= TimeUnit.SECONDS.toMillis(second)

        return when (minute) {
            0L -> String.format("%d : %d", second, totalMillis / 100)
            else -> String.format("%d : %d : %d", minute, second, totalMillis / 100)
        }
    }

}
