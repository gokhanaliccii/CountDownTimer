package com.gokhanaliccii.countdowntimer.countdown.formatter

import org.hamcrest.core.IsEqual.equalTo
import org.junit.Assert.assertThat
import org.junit.Test
import java.util.concurrent.TimeUnit

class RemainSecondFormatterTest {

    @Test
    fun `should show single digit for given millisecond `() {
        val givenMilliSecond = 999L
        val remainSecondFormatter = RemainSecondFormatter()

        assertThat(remainSecondFormatter.format(givenMilliSecond), equalTo("0 : 9"))
    }

    @Test
    fun `should show second with single millisecond for given millisecond`() {
        val givenMilliSecond = 1100L
        val remainSecondFormatter = RemainSecondFormatter()

        assertThat(remainSecondFormatter.format(givenMilliSecond), equalTo("1 : 1"))
    }

    @Test
    fun `should show minute and second with single millisecond for given millisecond`() {
        val givenMilliSecond = TimeUnit.MINUTES.toMillis(1) + TimeUnit.SECONDS.toMillis(3) + 99
        val remainSecondFormatter = RemainSecondFormatter()

        assertThat(remainSecondFormatter.format(givenMilliSecond), equalTo("1 : 3 : 0"))
    }


}