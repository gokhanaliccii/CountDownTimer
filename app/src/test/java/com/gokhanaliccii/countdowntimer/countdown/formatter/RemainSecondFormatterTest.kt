package com.gokhanaliccii.countdowntimer.countdown.formatter

import org.hamcrest.core.IsEqual.equalTo
import org.junit.Assert.assertThat
import org.junit.Test

class RemainSecondFormatterTest {

    @Test
    fun `should show single digit for given millisecond `() {
        val givenMilliSecond = 999L
        val remainSecondFormatter = RemainSecondFormatter()

        assertThat(remainSecondFormatter.format(givenMilliSecond), equalTo("9"))
    }
}