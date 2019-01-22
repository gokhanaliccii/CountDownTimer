package com.gokhanaliccii.countdowntimer.countdown.formatter

import java.util.concurrent.TimeUnit


class RemainSecondFormatter {

    fun format(millis: Long): String {
        var totalMillis = millis

        val minute = TimeUnit.MILLISECONDS.toMinutes(millis)
        totalMillis -= TimeUnit.MINUTES.toMillis(minute)
        val second = TimeUnit.MILLISECONDS.toSeconds(millis)
        totalMillis -= TimeUnit.SECONDS.toMillis(second)




        return String.format("%d : %d", second, totalMillis / 100)
    }

}
