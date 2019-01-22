package com.gokhanaliccii.countdowntimer.countdown

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gokhanaliccii.countdowntimer.common.display
import com.gokhanaliccii.countdowntimer.common.hide
import com.gokhanaliccii.countdowntimer.core.ViewModelFactory
import com.gokhanaliccii.countdowntimer.databinding.FragmentCountdownBinding

class CountDownTimerFragment : Fragment() {
    private lateinit var countDownViewModel: CountDownViewModel
    private lateinit var layoutBinding: FragmentCountdownBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        countDownViewModel =
                ViewModelProviders.of(this, ViewModelFactory())[CountDownViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        layoutBinding = FragmentCountdownBinding.inflate(inflater, container, false)
            .apply {
                viewModel = countDownViewModel
                setLifecycleOwner(this@CountDownTimerFragment)
            }

        return layoutBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        countDownViewModel.startCountDownTimer()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        countDownViewModel.uiState.observe(this, Observer {
            when (it) {
                is UIState.Completed -> {
                    layoutBinding.textCompleted.display()
                    layoutBinding.countingContainer.actionIncrement.hide()
                    layoutBinding.countingContainer.textRemainTime.hide()
                }
            }
        })
    }
}