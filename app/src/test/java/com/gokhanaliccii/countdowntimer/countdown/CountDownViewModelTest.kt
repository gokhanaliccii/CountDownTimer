package com.gokhanaliccii.countdowntimer.countdown

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.gokhanaliccii.countdowntimer.common.TEN_SECOND
import com.gokhanaliccii.countdowntimer.common.TWO_MINUTE
import com.gokhanaliccii.countdowntimer.countdown.checker.ExtensibilityBySecondChecker
import com.gokhanaliccii.countdowntimer.countdown.formatter.RemainSecondFormatter
import com.jraska.livedata.test
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule


class CountDownViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Test
    fun `should viewModel notify ui firstly for initial state`() {
        val viewModel = countDownViewModel(TWO_MINUTE)

        viewModel.uiState.test().assertValue {
            it == UIState.Initial
        }
    }

    @Test
    fun `should viewModel notify ui for counting`() {
        val viewModel = countDownViewModel(2)

        viewModel.startCountDownTimer()

        viewModel.uiState.test().assertValue {
            it == UIState.Initial
        }
    }

    private fun countDownViewModel(maxMillis: Long): CountDownViewModel {
        val remainSecondFormatter = RemainSecondFormatter()
        val timeExtensibilityChecker = ExtensibilityBySecondChecker(
            TEN_SECOND, maxMillis
        )

        return CountDownViewModel(
            maxMillis,
            remainSecondFormatter,
            timeExtensibilityChecker
        )
    }
}
