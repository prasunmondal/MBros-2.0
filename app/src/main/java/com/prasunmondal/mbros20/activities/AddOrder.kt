package com.prasunmondal.mbros20.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.prasunmondal.mbros20.DatabaseCalls.AddNewOrder
import com.prasunmondal.mbros20.R
import com.prasunmondal.mbros20.date.DateUtls
import com.prasunmondal.mbros20.models.Order

class AddOrder : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_order)
    }

    private fun getOrderId(): String {
        return DateUtls.getDate() + "_" + getCustomerId()
    }

    private fun getCustomerId(): String {
        return findViewById<EditText>(R.id.add_order_customerId).text.toString()
    }

    private fun getCustomerName(): String {
        return findViewById<EditText>(R.id.add_order_customerName).text.toString()
    }

    private fun getPcs(): String {
        return findViewById<EditText>(R.id.add_order_pcs).text.toString()
    }

    private fun getKilos(): String {
        return findViewById<EditText>(R.id.add_order_kilos).text.toString()
    }

    private fun getPricePerKilo(): String {
        return findViewById<EditText>(R.id.add_order_pricePerKilo).text.toString()
    }

    private fun getPreviousDue(): String {
        return findViewById<EditText>(R.id.add_order_previousDue).text.toString()
    }

    fun onClickPlaceOrderButton(view: View) {
        var order = Order(getOrderId(), getCustomerId(), getCustomerName(), getPcs(), getKilos(), getPricePerKilo(), getPreviousDue());
        AddNewOrder().execute(order, {})
    }
}