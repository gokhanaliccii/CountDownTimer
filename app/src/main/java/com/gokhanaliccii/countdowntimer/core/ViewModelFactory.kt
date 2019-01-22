package com.gokhanaliccii.countdowntimer.core

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.gokhanaliccii.countdowntimer.common.TEN_SECOND
import com.gokhanaliccii.countdowntimer.common.TWO_MINUTE
import com.gokhanaliccii.countdowntimer.countdown.CountDownViewModel
import com.gokhanaliccii.countdowntimer.countdown.checker.ExtensibilityBySecondChecker
import com.gokhanaliccii.countdowntimer.countdown.formatter.RemainSecondFormatter
import java.lang.IllegalArgumentException

class ViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CountDownViewModel::class.java)) {
            val remainSecondFormatter = RemainSecondFormatter()
            val timeExtensibilityChecker = ExtensibilityBySecondChecker(
                TEN_SECOND, TWO_MINUTE
            )

            return CountDownViewModel(
                TWO_MINUTE,
                remainSecondFormatter,
                timeExtensibilityChecker
            ) as T
        }

        throw IllegalArgumentException("Unknown class ${modelClass.name}")
    }
}