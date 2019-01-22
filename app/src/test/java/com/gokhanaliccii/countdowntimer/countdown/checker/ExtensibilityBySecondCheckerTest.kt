package com.gokhanaliccii.countdowntimer.countdown.checker

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.util.concurrent.TimeUnit

class ExtensibilityBySecondCheckerTest {

    companion object {
        const val TEN_SECOND = 10L
        const val TWO_MINUTE = 2 * 60L
    }

    private lateinit var timeExtensibility: TimeExtensibilityChecker

    @Before
    fun setUp() {
        timeExtensibility =
                ExtensibilityBySecondChecker(
                    TEN_SECOND,
                    TWO_MINUTE
                )
    }

    @Test
    fun `should time be extensible if remain time lower than 2 min`() {
        val remainSecond = TimeUnit.SECONDS.toSeconds(110)

        assertTrue(timeExtensibility.isTimeExtensible(remainSecond))
    }

    @Test
    fun `should time not extensible if remain time is 2 min`() {
        val remainSecond = TimeUnit.SECONDS.toSeconds(120)

        assertFalse(timeExtensibility.isTimeExtensible(remainSecond))
    }

    @Test
    fun `should time exceed max limit`() {
        val remainSecond = TimeUnit.SECONDS.toSeconds(111)

        assertTrue(timeExtensibility.isReachedToMaxLimit(remainSecond))
    }
}