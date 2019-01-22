package com.gokhanaliccii.countdowntimer.countdown.formatter


class RemainSecondFormatter {

    fun format(millis: Long): String {
        if (millis == 1000L) {
            return "0"
        }

        return String.format("%d", millis % 99)
    }

}
