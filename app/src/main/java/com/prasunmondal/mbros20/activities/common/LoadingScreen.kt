package com.prasunmondal.mbros20.activities.common

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.prasunmondal.lib.android.deviceinfo.Device
import com.prasunmondal.lib.android.deviceinfo.DeviceInfo
import com.prasunmondal.mbros20.R
import com.prasunmondal.mbros20.models.persons.customer.Customer
import com.prasunmondal.mbros20.models.persons.delivery_man.AppUser
import com.prasunmondal.mbros20.utils.Applog
import android.text.Editable

import android.text.TextWatcher





/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class LoadingScreen : AppCompatActivity() {

    enum class TaskPhase {
        LOADING_DATA, REGISTRATION
    }

    companion object {
        private var taskPhase: TaskPhase = TaskPhase.LOADING_DATA
    }

    private var mProgress: ProgressBar? = null
    private lateinit var label_loading_task: TextView
    private lateinit var editText_name: EditText
    private lateinit var btn_request_access: Button
    private var loadProgress = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_loading_screen)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setContentView(R.layout.activity_loading_screen)
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        mProgress = findViewById<View>(R.id.splash_screen_progress_bar) as ProgressBar
        label_loading_task = findViewById(R.id.label_loadingTask)
        initiallizeUI()
        configureUI(taskPhase)

        Thread {
            doWork()
            if(taskPhase == TaskPhase.LOADING_DATA)
                startApp()
//            finish()
        }.start()
    }

    private fun doWork() {
        val number_of_task = 4
        val padding = 10
        val eachStep = (100 - padding - padding)/number_of_task
//        while (loadProgress < 100) {
            loadProgress += padding
            mProgress!!.progress = loadProgress
            getUserDetails()
            getCustomerDetails(eachStep)
            getDetails3(eachStep)
            getDetails4(eachStep)
            configureUI(taskPhase)
            loadProgress += padding
            mProgress!!.progress = loadProgress
//        }
    }

    private fun initiallizeUI() {
        mProgress = findViewById<View>(R.id.splash_screen_progress_bar) as ProgressBar
        label_loading_task = findViewById(R.id.label_loadingTask)
        editText_name = findViewById(R.id.loading_screen_PersonName)
        btn_request_access = findViewById(R.id.loading_screen_save_button)
        onChangeNameText()
        editText_name.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                onChangeNameText()
            }
        })
    }

    private fun configureUI(taskPhase: TaskPhase) {
        println("Configure UI: " + taskPhase.name)
        this@LoadingScreen.runOnUiThread {
            if (taskPhase == TaskPhase.LOADING_DATA) {
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
    }

    private fun startApp() {
        val intent = Intent(this, Dashboard::class.java)
        startActivity(intent)
    }

    private fun getUserDetails() {
        Applog.startMethod(Throwable())
        Applog.info("TaskPhase: " + taskPhase.name, Throwable())
        label_loading_task.text = "Fetching User Details..."

//        AppUser.saveDetails(AppUser("Prasun", AppAccessRoles.ADMIN, AccessStatuses.APP_OPEN, "ffffffff-ff77-6cd3-0000-000075b319f8"))

        DeviceInfo.setContext(applicationContext, contentResolver)
        val phoneUID = DeviceInfo.get(Device.UNIQUE_ID)
        println("Device Id: $phoneUID")
        val appUser = AppUser.getDetails(phoneUID)
        if(appUser == null)
        {
            println("Device not registered.")
            taskPhase = TaskPhase.REGISTRATION
        } else {
            println("Login User Details: $appUser")
        }
        loadProgress += 20
        mProgress!!.progress = loadProgress
    }

    private fun getCustomerDetails(taskWeightage: Int) {
        Applog.startMethod(Throwable())
        Applog.info("TaskPhase: " + taskPhase.name, Throwable())
        label_loading_task.text = "Fetching Customer Details..."
        if(taskPhase != TaskPhase.LOADING_DATA) {
            loadProgress += taskWeightage
            mProgress!!.progress = loadProgress
            Thread.sleep(10)
            return
        }
        var p = Customer.fetchActiveCustomers(this)
        loadProgress += taskWeightage
        mProgress!!.progress = loadProgress
    }

    private fun getDetails3(taskWeightage: Int) {
        Applog.startMethod(Throwable())
        Applog.info("TaskPhase: " + taskPhase.name, Throwable())
        if(taskPhase != TaskPhase.LOADING_DATA) {
            loadProgress += taskWeightage
            mProgress!!.progress = loadProgress
            Thread.sleep(10)
            return
        }
        label_loading_task.text = "Fetching Orders..."
        loadProgress += taskWeightage
        mProgress!!.progress = loadProgress
    }

    private fun getDetails4(taskWeightage: Int) {
        Applog.startMethod(Throwable())
        Applog.info("TaskPhase: " + taskPhase.name, Throwable())
        if(taskPhase != TaskPhase.LOADING_DATA) {
            loadProgress += taskWeightage
            mProgress!!.progress = loadProgress
            Thread.sleep(10)
            return
        }
        label_loading_task.text = "Fetching Current Status..."
            loadProgress += taskWeightage
            mProgress!!.progress = loadProgress
    }

    fun onChangeNameText() {
        if(editText_name.text.isEmpty()) {
            btn_request_access.setEnabled(false)
        } else {
            btn_request_access.setEnabled(true)
        }
    }
}