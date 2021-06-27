package com.prasunmondal.mbros20.activities.common

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.prasunmondal.mbros20.R
import com.prasunmondal.lib.android.deviceinfo.Device
import com.prasunmondal.lib.android.deviceinfo.DeviceInfo
import com.prasunmondal.mbros20.models.persons.customer.Customer
import com.prasunmondal.mbros20.models.persons.delivery_man.AppUser


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */

class LoadingScreen : AppCompatActivity() {

    enum class TaskPhase {
        LOADING_DATA, REGISTRATION
    }

    private var mProgress: ProgressBar? = null
    private lateinit var label_loading_task: TextView
    private lateinit var editText_name: EditText
    private lateinit var btn_request_access: Button
    var loadProgress = 0
    private var taskPhase: TaskPhase = TaskPhase.LOADING_DATA

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading_screen)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setContentView(R.layout.activity_loading_screen)

        taskPhase = TaskPhase.LOADING_DATA
        initiallizeUI()
        configureUI(taskPhase)
        loadProgress += 10
        mProgress!!.progress = loadProgress

        runOnUiThread {
            doWork()
            startApp()
            finish()
        }
    }

    private fun initiallizeUI() {
        mProgress = findViewById<View>(R.id.splash_screen_progress_bar) as ProgressBar
        label_loading_task = findViewById<TextView>(R.id.label_loadingTask)
        editText_name = findViewById<EditText>(R.id.loading_screen_PersonName)
        btn_request_access = findViewById<Button>(R.id.loading_screen_save_button)
    }

    private fun configureUI(taskPhase: TaskPhase) {
        println("Configure UI: " + taskPhase.name)
        if(taskPhase == TaskPhase.LOADING_DATA) {
            mProgress!!.visibility = View.VISIBLE
            label_loading_task.visibility = View.VISIBLE
            editText_name.visibility = View.INVISIBLE
            btn_request_access.visibility = View.INVISIBLE
        } else {
            mProgress!!.visibility = View.INVISIBLE
            label_loading_task.visibility = View.INVISIBLE
            editText_name.visibility = View.VISIBLE
            btn_request_access.visibility = View.VISIBLE
        }
    }

    private fun doWork() {

        var number_of_task = 4;
        var padding = 10
        var eachStep = (100 - padding - padding)/number_of_task
        while (loadProgress < 100) {

            getUserDetails(eachStep)
            getCustomerDetails(eachStep)
            getDetails3(eachStep)
            getDetails4(eachStep)
            loadProgress += padding
            mProgress!!.progress = loadProgress
        }
    }

    private fun startApp() {
        val intent = Intent(this, Dashboard::class.java)
        startActivity(intent)
    }

    private fun getUserDetails(taskWeightage: Int) {
        label_loading_task.text = "Fetching User Details..."

//        AppUser.saveDetails(AppUser("Prasun", AppAccessRoles.ADMIN, AccessStatuses.APP_OPEN, "ffffffff-ff77-6cd3-0000-000075b319f8"))

        DeviceInfo.setContext(applicationContext, contentResolver)
        val phoneUID = DeviceInfo.get(Device.UNIQUE_ID)
        println("Device Id: $phoneUID")
        var appUser = AppUser.getDetails(phoneUID)
        if(appUser == null)
        {
            println("Device not registered.")
            taskPhase = TaskPhase.REGISTRATION
            configureUI(taskPhase)
        } else {
            println("Login User Details: " + appUser)
        }
        loadProgress += taskWeightage
        mProgress!!.progress = loadProgress
    }

    private fun getCustomerDetails(taskWeightage: Int) {
        if(taskPhase != TaskPhase.LOADING_DATA)
            return
        label_loading_task.text = "Fetching Customer Details..."
        var p = Customer.fetchActiveCustomers(this)
        loadProgress += taskWeightage
        mProgress!!.progress = loadProgress
    }

    private fun getDetails3(taskWeightage: Int) {
        if(taskPhase != TaskPhase.LOADING_DATA)
            return
        label_loading_task.text = "Fetching Orders..."
        loadProgress += taskWeightage
        mProgress!!.progress = loadProgress
    }

    private fun getDetails4(taskWeightage: Int) {
        if(taskPhase != TaskPhase.LOADING_DATA)
            return
        label_loading_task.text = "Fetching Current Status..."
        loadProgress += taskWeightage
        mProgress!!.progress = loadProgress
    }
}