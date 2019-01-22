package com.gokhanaliccii.countdowntimer.countdown

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.gokhanaliccii.countdowntimer.countdown.UIState.*
import com.gokhanaliccii.countdowntimer.countdown.checker.ExtensibilityBySecondChecker
import com.gokhanaliccii.countdowntimer.countdown.formatter.RemainSecondFormatter
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicLong

class CountDownViewModel : ViewModel() {

    private companion object {
        const val TWO_MINUTE = 120000L
        const val TEN_SECOND = 10000L
        const val HUNDRED_MILLI_SECOND = 100L
        const val INITIAL_DISPLAYED_TEXT = "2 : 0 : 0"
    }

    val uiState = MutableLiveData<UIState>()
    val displayedText = MutableLiveData<String>()

    private lateinit var disposable: Disposable
    private val remainSecondFormatter = RemainSecondFormatter()
    private val timeExtensibility = ExtensibilityBySecondChecker(
        TEN_SECOND, TWO_MINUTE
    )

    private val remainMillis = AtomicLong(TWO_MINUTE)


    init {
        uiState.postValue(Initial)
        displayedText.postValue(INITIAL_DISPLAYED_TEXT)
    }

    fun start() {
        uiState.postValue(Counting)

        disposable = Observable.interval(HUNDRED_MILLI_SECOND, TimeUnit.MILLISECONDS)
            .takeUntil {
                remainMillis.get() <= 0L
            }.map {
                (remainMillis.get() - it)
            }.doOnNext {
                remainMillis.set(it!!)
            }.map(remainSecondFormatter::format)
            .doOnComplete {
                uiState.postValue(Completed)
            }
            .subscribe {
                displayedText.postValue(it)
            }
    }

    fun incrementRemain() {
        val remain = remainMillis.get()

        if (timeExtensibility.isTimeExtensible(remain)) {
            remainMillis.set(remain + TimeUnit.SECONDS.toMillis(10))
        } else if (timeExtensibility.isTimeExtensible(remain)) {
            reset()
        }
    }

    private fun reset() {
        remainMillis.set(TWO_MINUTE)
        displayedText.postValue(INITIAL_DISPLAYED_TEXT)
        uiState.postValue(Counting)
    }

    override fun onCleared() {
        super.onCleared()

        disposable.dispose()
    }
}