package com.prasunmondal.mbros20.activities.common

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.prasunmondal.mbros20.R
import com.prasunmondal.lib.android.deviceinfo.Device
import com.prasunmondal.lib.android.deviceinfo.DeviceInfo
import com.prasunmondal.lib.android.deviceinfo.InstalledApps
import com.prasunmondal.mbros20.models.persons.AccessStatuses
import com.prasunmondal.mbros20.models.persons.AppAccessRoles
import com.prasunmondal.mbros20.models.persons.delivery_man.Person


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
            getUserDetails()
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
        val intent = Intent(this, Dashboard::class.java)
        startActivity(intent)
    }

    private fun getUserDetails() {
        label_loading_task.text = "Fetching User Details..."

        Person.saveDetails(Person("Prasun", AppAccessRoles.ADMIN, AccessStatuses.APP_OPEN, "ffffffff-ff77-6cd3-0000-000075b319f8"))

        DeviceInfo.setContext(applicationContext, contentResolver)
        val phoneUID = DeviceInfo.get(Device.UNIQUE_ID)
        println("Device Id: $phoneUID")
        println("Login User Details: " + Person.getDetails(phoneUID))
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