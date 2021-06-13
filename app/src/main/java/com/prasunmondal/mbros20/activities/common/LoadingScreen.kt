package com.prasunmondal.mbros20.activities.common

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.prasunmondal.mbros20.R
import com.prasunmondal.mbros20.activities.UserRoleSelect


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class LoadingScreen : AppCompatActivity() {

    private var mProgress: ProgressBar? = null
    private lateinit var label_loading_task: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_loading_screen)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setContentView(R.layout.activity_loading_screen)
        mProgress = findViewById<View>(R.id.splash_screen_progress_bar) as ProgressBar
        label_loading_task = findViewById(R.id.label_loadingTask)
        Thread {
            doWork()
            startApp()
            finish()
        }.start()
    }

    private fun doWork() {
        var progress = 0
        var number_of_task = 4;
        var padding = 10
        var eachStep = (100 - padding - padding)/number_of_task
        while (progress < 100) {
            progress += padding
            mProgress!!.progress = progress
            getDetails1()
            progress += eachStep
            mProgress!!.progress = progress
            getDetails2()
            progress += eachStep
            mProgress!!.progress = progress
            getDetails3()
            progress += eachStep
            mProgress!!.progress = progress
            getDetails4()
            progress += eachStep
            mProgress!!.progress = progress
            progress += padding
            mProgress!!.progress = progress
        }
    }

    private fun startApp() {
        val intent = Intent(this, UserRoleSelect::class.java)
        startActivity(intent)
    }

    private fun getDetails1() {
        label_loading_task.text = "Fetching User Details..."
        Thread.sleep(1000)
    }

    private fun getDetails2() {
        label_loading_task.text = "Fetching Customer Details..."
        Thread.sleep(1000)
    }

    private fun getDetails3() {
        label_loading_task.text = "Fetching Orders..."
        Thread.sleep(1000)
    }

    private fun getDetails4() {
        label_loading_task.text = "Fetching Current Status..."
        Thread.sleep(1000)
    }
}