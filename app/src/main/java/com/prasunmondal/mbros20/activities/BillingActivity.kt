package com.prasunmondal.mbros20.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.prasunmondal.mbros20.R
import com.prasunmondal.mbros20.models.Delivery
import com.prasunmondal.mbros20.models.Order

class BillingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_billing)

        Log.d("Billing Activity: ", "onCreate")
        val bundle = intent.extras
        if(bundle != null) {
            Log.d("Billing Activity: ", "bundle is not null")
            val deliveryObject: Delivery? = bundle.getSerializable("deliveryObject") as Delivery?
            if (deliveryObject != null) {
                Log.d("Billing Activity: ", "delivery object is not null")
                prepareBillingUI(deliveryObject)
            } else {
                Log.d("Billing Activity: ", "delivery object is null")
            }
        } else {
            Log.d("Billing Activity: ", "bundle is null")
        }
    }

    private fun prepareBillingUI(deliveryObject: Delivery) {
        Log.d("Billing Activity: ",
            deliveryObject.kilos + ", "
                    + deliveryObject.pcs + ", "
                    + deliveryObject.previousDue + ", "
                    + deliveryObject.pricePerKilo + ", "
                    + deliveryObject.todayPaid + ", "
                    + deliveryObject.customerName + ", ")
    }
}