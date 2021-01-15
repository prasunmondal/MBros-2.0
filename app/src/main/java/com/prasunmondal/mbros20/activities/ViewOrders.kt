package com.prasunmondal.mbros20.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.prasunmondal.mbros20.R
import com.prasunmondal.mbros20.database_calls.OrdersFetch
import com.prasunmondal.mbros20.models.Order
import com.prasunmondal.mbros20.models.OrderList
import java.util.*

class ViewOrders : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_orders)

        val addOrderBtn = findViewById<FloatingActionButton>(R.id.view_orders_addOrderBtn)
        addOrderBtn.setOnClickListener{
            goToAddOrderActivity(null)
        }
        OrderList.instance.getFromFile(this)
        if(OrderList.instance.list.isNotEmpty())
            showCustomerData(OrderList.instance.list)
    }

    private fun goToAddOrderActivity(order: Order?) {
        val myIntent = Intent(this, AddOrder::class.java)
        if(order != null) {
            val bundle = Bundle()
            bundle.putSerializable("order", order)
            myIntent.putExtras(bundle)
        }
        this.startActivity(myIntent)
    }

    private fun showCustomerData(orders: ArrayList<Order>) {
        val view = findViewById<LinearLayout>(R.id.view_orders_viewArea)
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
        goToAddOrderActivity(order)
    }
}