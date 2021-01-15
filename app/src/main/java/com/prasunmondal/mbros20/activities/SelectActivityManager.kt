package com.prasunmondal.mbros20.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.prasunmondal.mbros20.R

class SelectActivityManager : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_manager)
    }

    fun onClickActivitySelectManagerViewCustomerDetailsBtn(view: View) {
        val myIntent = Intent(this, ViewCustomers::class.java)
        this.startActivity(myIntent)
    }

    fun onClickActivitySelectManagerAddCustomerBtn(view: View) {
        val myIntent = Intent(this, AddCustomer::class.java)
        this.startActivity(myIntent)
    }

    fun onClickActivitySelectManagerOrdersBtn(view: View) {
        val myIntent = Intent(this, ViewOrders::class.java)
        this.startActivity(myIntent)
    }
}