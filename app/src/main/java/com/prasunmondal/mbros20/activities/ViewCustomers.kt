package com.prasunmondal.mbros20.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.prasunmondal.mbros20.DatabaseCalls.FetchAllCustomers
import com.prasunmondal.mbros20.models.Customer
import com.prasunmondal.mbros20.models.CustomerList
import com.prasunmondal.mbros20.R
import java.lang.Exception
import java.util.ArrayList
import java.util.function.Consumer

class ViewCustomers : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_customers)

        try {
            var customers = CustomerList.getFromFile(this);
            System.out.println(customers);
            if(customers != null) {
                showCustomerData(customers)
            }
        } catch (e: Exception) {
            System.out.println("error prasun");
            System.out.println(e);
        }
    }

    fun showCustomerData(customers: ArrayList<Customer>) {
        var view = findViewById<TextView>(R.id.view_customers_viewArea);
        var str = ""
        for(customer in customers) {
            str += customer.name + "\n"
        }
        view.text = str
    }

    fun saveAndDisplayCustomers(customers: ArrayList<Customer>) {
        CustomerList.list = customers
        CustomerList.saveToFile(this)
        showCustomerData(CustomerList.list)
    }

    fun onClickRefreshCustomerListButton(view: View) {
        FetchAllCustomers.execute(Consumer { p1-> saveAndDisplayCustomers(p1)});
    }
}