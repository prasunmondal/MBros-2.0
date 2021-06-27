package com.prasunmondal.mbros20

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.prasunmondal.lib.android.deviceinfo.Device
import com.prasunmondal.lib.android.deviceinfo.DeviceInfo
import com.prasunmondal.mbros20.models.persons.AccessStatuses
import com.prasunmondal.mbros20.models.persons.delivery_man.AppUser

class Login : AppCompatActivity() {

    private lateinit var editText_name: EditText
    private lateinit var btn_request_access: Button
    private lateinit var text_request_access: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initiallizeUI()

        val appUser = fetchReceivedData()
        if(appUser == null) {
            // no entry found
            setUINoEntryFound()
        } else if (appUser.accessStatus != AccessStatuses.ACTIVE) {
            // waiting for approval
            setUIWaitingForApproval()
        } else {
            // approved
            goToNextPage()
        }
    }

    private fun setUIWaitingForApproval() {
        editText_name.visibility = View.INVISIBLE
        btn_request_access.visibility = View.INVISIBLE
        text_request_access.text = "Your approval is pending."
    }

    private fun setUINoEntryFound() {
        editText_name.visibility = View.VISIBLE
        btn_request_access.visibility = View.VISIBLE
        text_request_access.text = "You are not registered.\nPlease request for access below."
    }

    private fun goToNextPage() {

    }

    private fun fetchReceivedData(): AppUser? {
        val bundle = intent.extras
        if(bundle!!.getSerializable("appUser") == null)
            return null
        return bundle!!.getSerializable("appUser")!! as AppUser
    }

    private fun initiallizeUI() {
        editText_name = findViewById(R.id.loading_screen_PersonName)
        btn_request_access = findViewById(R.id.loading_screen_save_button)
        text_request_access = findViewById(R.id.loading_screen_label_user_not_registered2)

        onChangeNameText()
        editText_name.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                onChangeNameText()
            }
        })
    }

    fun onClickRegisterButton(view: View) {
        var customer = AppUser(editText_name.text.toString(), DeviceInfo.get(Device.UNIQUE_ID))
        val response = AppUser.saveDetails(customer)
        if(response.numberOfRecordsAdded() > 0) {
            // Data created - wait for approval
            setUIWaitingForApproval()
        }
    }

    fun onChangeNameText() {
        if(editText_name.text.isEmpty()) {
            btn_request_access.setEnabled(false)
        } else {
            btn_request_access.setEnabled(true)
        }
    }
}