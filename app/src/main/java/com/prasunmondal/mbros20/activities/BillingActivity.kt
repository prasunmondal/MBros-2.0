package com.prasunmondal.mbros20.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.prasunmondal.mbros20.R
import com.prasunmondal.mbros20.calculators.Calculator
import com.prasunmondal.mbros20.date.DateUtils
import com.prasunmondal.mbros20.models.Delivery
import com.prasunmondal.mbros20.models.OrderList
import com.prasunmondal.mbros20.session_data.SubmitDeliveryActivity
import java.lang.Exception

class BillingActivity : AppCompatActivity() {

    private lateinit var textViewTodaysAmount: TextView
    private lateinit var textViewPreviousAmount: TextView
    private lateinit var textViewTotalAmount: TextView
    private lateinit var editTextTodaysPayment: EditText
    private lateinit var textViewRemainingAmount: TextView

//    private lateinit var order: Order/
    private lateinit var deliveryObject: Delivery

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_billing)

        textViewTodaysAmount = findViewById(R.id.billing_todaysAmount)
        textViewPreviousAmount = findViewById(R.id.billing_prevDue)
        textViewTotalAmount = findViewById(R.id.billing_totalAmount)
        editTextTodaysPayment = findViewById(R.id.billing_todaysPayment)
        textViewRemainingAmount = findViewById(R.id.billing_remainingAmount)

        editTextTodaysPayment.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                setRemainingAmount()
            }
        })

        Log.d("Billing Activity: ", "onCreate")
        val bundle = intent.extras
        if(bundle != null) {
            Log.d("Billing Activity: ", "bundle is not null")
            deliveryObject = bundle.getSerializable("deliveryObject")!! as Delivery
            Log.d("Billing Activity: ", "delivery object is not null")
//            order = OrderList.instance.getOrderByCustomerId(deliveryObject.customerId)!!
            prepareBillingUI()
        } else {
            Log.d("Billing Activity: ", "bundle is null")
        }
    }

    private fun prepareBillingUI() {
        Log.d("Billing Activity: ",
            deliveryObject.kilos + ", "
                    + deliveryObject.pcs + ", "
                    + deliveryObject.previousDue + ", "
                    + deliveryObject.pricePerKilo + ", "
                    + deliveryObject.todayPaid + ", "
                    + deliveryObject.customerName + ", ")

        Log.d("OrderList:", OrderList.instance.toString())
        Log.d("deliveryObject:", deliveryObject.customerId)
        textViewTodaysAmount.text = Calculator.deliveryTotalPriceForTodaysDelivery(
            deliveryObject,
                OrderList.instance.getOrderByCustomerId(
                        deliveryObject.customerId)!!).toString()

        textViewPreviousAmount.text = OrderList.instance.getOrderByCustomerId(deliveryObject.customerId)!!.previousDue

        textViewTotalAmount.text = Calculator.deliveryTotalAmount_today_prevDue(deliveryObject,
            OrderList.instance.getOrderByCustomerId(deliveryObject.customerId)!!).toString()

        setRemainingAmount()
    }

    private fun setRemainingAmount() {
        Log.d("BillingActivity: ", "setRemainingAmount")
        textViewRemainingAmount.text = Calculator.deliveryRemainingAmount(deliveryObject,
            OrderList.instance.getOrderByCustomerId(deliveryObject.customerId)!!, getPaidAmount()).toString()
    }

    private fun getPaidAmount(): Float {
        return try {
            editTextTodaysPayment.text.toString().toFloat()
        } catch (e: Exception) {
            0.0F
        }
    }

    fun onClickBillingSubmitBtn(view: View) {
        saveData()
        val myIntent = Intent(this, SubmitDeliveryActivity::class.java)
        val bundle = Bundle()
        bundle.putSerializable("deliveryObject", deliveryObject)
        myIntent.putExtras(bundle)
        this.startActivity(myIntent)
    }

    private fun saveData() {
        deliveryObject.todayPaid = getPaidAmount().toInt().toString()
        deliveryObject.pcs = Calculator.deliveryTotalPc(deliveryObject).toString()
        deliveryObject.kilos = Calculator.deliveryTotalKg(deliveryObject).toString()
        deliveryObject.deliveryDate = DateUtils.getDate()
        deliveryObject.deliveryTime = DateUtils.getTime()
    }
}