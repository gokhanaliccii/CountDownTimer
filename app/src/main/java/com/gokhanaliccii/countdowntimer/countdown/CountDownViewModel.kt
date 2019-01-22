package com.gokhanaliccii.countdowntimer.countdown

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.gokhanaliccii.countdowntimer.common.HUNDRED_MILLI_SECOND
import com.gokhanaliccii.countdowntimer.common.TEN_SECOND
import com.gokhanaliccii.countdowntimer.countdown.UIState.*
import com.gokhanaliccii.countdowntimer.countdown.checker.TimeExtensibilityChecker
import com.gokhanaliccii.countdowntimer.countdown.formatter.RemainSecondFormatter
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicLong

class CountDownViewModel(
    private val maxMillis: Long,
    private val remainSecondFormatter: RemainSecondFormatter,
    private val timeExtensibilityChecker: TimeExtensibilityChecker
) : ViewModel() {

    private companion object {
        const val INITIAL_DISPLAYED_TEXT = "2 : 0 : 0"
    }

    val uiState = MutableLiveData<UIState>()
    val displayedText = MutableLiveData<String>()

    private lateinit var disposable: Disposable
    private val remainMillis = AtomicLong(maxMillis)

    init {
        uiState.postValue(Initial)
        displayedText.postValue(INITIAL_DISPLAYED_TEXT)
    }

    fun startCountDownTimer() {
        uiState.postValue(Counting)

        disposable = Observable.interval(HUNDRED_MILLI_SECOND, TimeUnit.MILLISECONDS)
            .map(this::toRemainingMillis)
            .takeWhile(this::untilZero)
            .doOnNext(this::updateRemainMillis)
            .map(remainSecondFormatter::format)
            .doOnComplete(this::notifyCountingCompleted)
            .subscribe {
                displayedText.postValue(it)
            }
    }

    private fun untilZero(millis: Long): Boolean = millis > 0

    private fun toRemainingMillis(millis: Long) = (remainMillis.get() - millis)

    private fun updateRemainMillis(millis: Long) = remainMillis.set(millis)

    private fun notifyCountingCompleted() = uiState.postValue(Completed)

    fun incrementRemain() {
        val remain = remainMillis.get()

        when {
            timeExtensibilityChecker.isTimeExtensible(remain) -> remainMillis.set(remain + TEN_SECOND)
            timeExtensibilityChecker.isReachedToMaxLimit(remain) -> reset()
        }
    }

    private fun reset() {
        remainMillis.set(maxMillis)
        displayedText.postValue(INITIAL_DISPLAYED_TEXT)
        uiState.postValue(Counting)
    }

    override fun onCleared() {
        super.onCleared()

        disposable.dispose()
    }
}