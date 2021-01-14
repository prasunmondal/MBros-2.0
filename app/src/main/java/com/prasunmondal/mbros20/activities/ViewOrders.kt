package com.prasunmondal.mbros20.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.prasunmondal.mbros20.DatabaseCalls.OrdersFetch
import com.prasunmondal.mbros20.models.Order
import com.prasunmondal.mbros20.R
import java.util.ArrayList

class ViewOrders : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_orders)
    }

    fun showCustomerData(orders: ArrayList<Order>) {
        var view = findViewById<TextView>(R.id.view_orders_viewArea);
        var str = ""
        for(order in orders) {
            str += order.toString() + "\n"
        }
        view.text = str
    }

    fun onClickRefreshOrdersButton(view: View) {
        OrdersFetch.execute( { p1-> showCustomerData(p1)});
    }
}