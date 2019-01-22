package com.gokhanaliccii.countdowntimer

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        savedInstanceState.let {
            supportFragmentManager.beginTransaction()
                .replace(R.id.content_frame, CountDownTimerFragment())
                .commit()
        }

    }
}
