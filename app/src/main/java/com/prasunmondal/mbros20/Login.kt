package com.prasunmondal.mbros20

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.prasunmondal.lib.android.deviceinfo.Device
import com.prasunmondal.lib.android.deviceinfo.DeviceInfo
import com.prasunmondal.mbros20.models.persons.delivery_man.AppUser

class Login : AppCompatActivity() {

    private lateinit var editText_name: EditText
    private lateinit var btn_request_access: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initiallizeUI()
    }

    private fun initiallizeUI() {
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

    fun onClickRegisterButton(view: View) {
        var customer = AppUser(editText_name.text.toString(), DeviceInfo.get(Device.UNIQUE_ID))
        val response = AppUser.saveDetails(customer)
        if(response.numberOfRecordsAdded() > 0) {
            // Data created - wait for approval

        } else {
            // Data already exist with same name or same phoneUID
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