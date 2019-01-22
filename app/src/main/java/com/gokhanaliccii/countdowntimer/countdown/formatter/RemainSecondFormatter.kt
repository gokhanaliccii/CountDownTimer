package com.gokhanaliccii.countdowntimer.countdown.formatter

import com.gokhanaliccii.countdowntimer.countdown.toTimePart


class RemainSecondFormatter {

    fun format(millis: Long): String {
        var timePart = toTimePart(millis)

        return when (timePart.minute) {
            0L -> String.format("%d : %d", timePart.second, timePart.millis / 100)
            else -> String.format(
                "%d : %d : %d",
                timePart.minute,
                timePart.second,
                timePart.millis / 100
            )
        }
    }

}
