package com.prasunmondal.mbros20.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.prasunmondal.mbros20.database_calls.OrdersFetch
import com.prasunmondal.mbros20.models.Order
import com.prasunmondal.mbros20.R
import com.prasunmondal.mbros20.models.CustomerList.Companion.saveToFile
import com.prasunmondal.mbros20.models.OrderList
import com.prasunmondal.mbros20.session_data.SessionData
import java.util.ArrayList

class ViewOrders : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_orders)
        OrderList.instance.getFromFile(this)
        if(OrderList.instance.list!!.isNotEmpty())
            showCustomerData(OrderList.instance.list)
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
        OrdersFetch.execute { p1 ->
            OrderList.instance.saveToFile(this, p1)
            showCustomerData(p1)
        };

    }
}