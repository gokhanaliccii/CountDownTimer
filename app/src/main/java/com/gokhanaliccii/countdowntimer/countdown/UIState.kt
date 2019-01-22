package com.gokhanaliccii.countdowntimer.countdown

sealed class UIState {
    object Initial : UIState()

    object  Counting : UIState()

    object Completed : UIState()
}
