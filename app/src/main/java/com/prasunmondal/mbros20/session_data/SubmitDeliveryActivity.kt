package com.prasunmondal.mbros20.session_data

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.prasunmondal.mbros20.R
import com.prasunmondal.mbros20.models.CustomerList
import com.prasunmondal.mbros20.models.Delivery
import com.prasunmondal.mbros20.models.OrderList

class SubmitDeliveryActivity : AppCompatActivity() {

    private lateinit var deliveryObject: Delivery

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_submit_delivery)

        val bundle = intent.extras
        if(bundle != null) {
            Log.d("Billing Activity: ", "bundle is not null")
            deliveryObject = bundle.getSerializable("deliveryObject")!! as Delivery
            Log.d("Billing Activity: ", "delivery object is not null")
//            order = OrderList.instance.getOrderByCustomerId(deliveryObject.customerId)!!
//            prepareBillingUI()
        } else {
            Log.d("Billing Activity: ", "bundle is null")
        }
        submitData()
    }

    private fun submitData() {
        System.out.println(deliveryObject)
        System.out.println(CustomerList.getCustomerById(deliveryObject.customerId))
        System.out.println(OrderList.instance.getOrderByCustomerId(deliveryObject.customerId))
    }
}