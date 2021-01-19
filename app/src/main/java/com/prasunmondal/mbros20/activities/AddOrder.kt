package com.prasunmondal.mbros20.activities

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.prasunmondal.mbros20.R
import com.prasunmondal.mbros20.database_calls.OrderAdd
import com.prasunmondal.mbros20.date.DateUtils
import com.prasunmondal.mbros20.models.Order

class AddOrder : AppCompatActivity() {

    private lateinit var customerIdInput: EditText
    private lateinit var customerNameInput: EditText
    private lateinit var pcInput: EditText
    private lateinit var kiloInput: EditText
    private lateinit var pricePerKiloInput: EditText
    private lateinit var previousDueInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_order)

        customerIdInput = findViewById(R.id.add_order_customerId)
        customerNameInput = findViewById(R.id.add_order_customerName)
        pcInput = findViewById(R.id.add_order_pcs)
        kiloInput = findViewById(R.id.add_order_kilos)
        pricePerKiloInput = findViewById(R.id.add_order_pricePerKilo)
        previousDueInput = findViewById(R.id.add_order_previousDue)

        val bundle = intent.extras
        if(bundle != null) {
            val order: Order? = bundle.getSerializable("order") as Order?
            if (order != null) {
                prepareEditUI(order)
            }
        }
    }

    private fun prepareEditUI(order: Order) {
        customerIdInput.setText(order.customerId)
        customerNameInput.setText(order.customerName)
        pcInput.setText(order.pcs)
        kiloInput.setText(order.kilos)
        pricePerKiloInput.setText(order.pricePerKilo)
        previousDueInput.setText(order.previousDue)
    }

    private fun getOrderId(): String {
        return DateUtils.getDate() + "_" + getCustomerId()
    }

    private fun getCustomerId(): String {
        return customerIdInput.text.toString()
    }

    private fun getCustomerName(): String {
        return customerNameInput.text.toString()
    }

    private fun getPcs(): String {
        return pcInput.text.toString()
    }

    private fun getKilos(): String {
        return kiloInput.text.toString()
    }

    private fun getPricePerKilo(): String {
        return pricePerKiloInput.text.toString()
    }

    private fun getPreviousDue(): String {
        return previousDueInput.text.toString()
    }

    fun onClickPlaceOrderButton(view: View) {
        val order = Order(getOrderId(), getCustomerId(), getCustomerName(), getPcs(), getKilos(), getPricePerKilo(), getPreviousDue())
        OrderAdd().execute(order, {})
    }
}