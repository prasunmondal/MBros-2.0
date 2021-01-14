package com.prasunmondal.mbros20.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.prasunmondal.mbros20.models.Customer
import com.prasunmondal.mbros20.R

class RegisterCustomer : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_customer)

    }

    private fun getUserId(): String {
        return findViewById<TextView>(R.id.registerCustomer_userId).text.toString()
    }

    private fun getName(): String {
        return findViewById<TextView>(R.id.registerCustomer_name).text.toString()
    }

    private fun getPhoneNumber1(): String {
        return findViewById<TextView>(R.id.registerCustomer_phoneNumber).text.toString()
    }

    fun onClickSaveButton(view: View) {
        var customer = Customer(getUserId(), getName(), getPhoneNumber1(), "", "")
        customer.addToDB()
    }
}