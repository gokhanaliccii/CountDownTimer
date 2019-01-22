package com.gokhanaliccii.countdowntimer

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gokhanaliccii.countdowntimer.countdown.CountDownViewModel
import com.gokhanaliccii.countdowntimer.countdown.UIState
import com.gokhanaliccii.countdowntimer.databinding.FragmentCountdownBinding

class CountDownTimerFragment : Fragment() {

    lateinit var viewModel: CountDownViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this)[CountDownViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layoutBinding = FragmentCountdownBinding.inflate(inflater, container, false)
        layoutBinding.setLifecycleOwner(this)
        layoutBinding.viewModel = viewModel

        return layoutBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.start()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.uiState.observe(this, Observer {
            when (it) {
                is UIState.Completed -> {
                
                }
            }
        })
    }
}