package com.prasunmondal.mbros20

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.prasunmondal.mbros20.activities.ViewOrders
import com.prasunmondal.mbros20.database_calls.CustomersFetchAll
import com.prasunmondal.mbros20.database_calls.OrdersFetch
import com.prasunmondal.mbros20.models.CustomerList
import com.prasunmondal.mbros20.models.OrderList

class PrepareAppDataFetch : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prepare_app_data_fetch)

        prepareCustomerList()
    }

    fun prepareCustomerList() {
        Log.d("PrepareAppDataFetch:","Fetching Customers")
        var customerList = CustomerList.getFromFile(this)
        if(customerList==null) {
            Log.d("PrepareAppDataFetch:","Fetching Customers")
            CustomersFetchAll.execute{ customers ->
                Log.d("PrepareAppDataFetch:","Fetching Customers - done")
                CustomerList.list = customers
                CustomerList.saveToFile(this)
                prepareOrderList()
            }
        } else {
            prepareOrderList()
        }
    }

    fun prepareOrderList() {
        Log.d("PrepareAppDataFetch:","Fetching Orders")
        var orderList = OrderList.instance.getFromFile(this)
        if(orderList==null) {
            OrdersFetch.execute{ orders ->
                OrderList.instance.list = orders
                OrderList.instance.saveToFile(this, OrderList.instance.list)
                goToViewOrdersActivtiy()
            }
        } else {
            goToViewOrdersActivtiy()
        }
    }

    fun goToViewOrdersActivtiy() {
        val myIntent = Intent(this, ViewOrders::class.java)
        this.startActivity(myIntent)
        finish()
    }
}