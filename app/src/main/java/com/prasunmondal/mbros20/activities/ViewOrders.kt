package com.prasunmondal.mbros20.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.prasunmondal.mbros20.database_calls.OrdersFetch
import com.prasunmondal.mbros20.models.Order
import com.prasunmondal.mbros20.R
import com.prasunmondal.mbros20.models.OrderList
import java.util.ArrayList

class ViewOrders : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_orders)
        OrderList.instance.getFromFile(this)
        if(OrderList.instance.list.isNotEmpty())
            showCustomerData(OrderList.instance.list)
    }

    fun showCustomerData(orders: ArrayList<Order>) {
        var view = findViewById<LinearLayout>(R.id.view_orders_viewArea);

        for(order in orders) {
            val orderCard = LinearLayout(this)
            val text = TextView(this)
            text.text = order.toString()
            orderCard.addView(text)
            view.addView(orderCard)

            orderCard.setOnClickListener {
                onClickOrderEdit(order)
            }
        }
    }

    fun onClickRefreshOrdersButton(view: View) {
        OrdersFetch.execute { p1 ->
            OrderList.instance.saveToFile(this, p1)
            showCustomerData(p1)
        }
    }

    private fun onClickOrderEdit(order: Order) {
        // TODO: Go to edit order with the order details
        println("Go to edit order: $order")
    }
}