package com.prasunmondal.mbros20.activities

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.prasunmondal.mbros20.R
import com.prasunmondal.mbros20.models.CustomerFullDetails
import com.prasunmondal.mbros20.models.CustomerList
import com.prasunmondal.mbros20.models.Delivery
import com.prasunmondal.mbros20.models.Order
import java.io.Serializable


class Denominations(var pc: String, var kg: String): Serializable {
    fun getPcInt(): Int {
        return pc.toInt()
    }

    fun getKgFloat(): Float {
        return kg.toFloat()
    }

    override fun toString(): String {
        return "pc: $pc | kg: $kg"
    }
}

class DeliveryDenominations : AppCompatActivity() {
    private lateinit var pcInput: EditText
    private lateinit var kgInput: EditText
    private lateinit var totalPc: TextView
    private lateinit var totalKg: TextView
    private lateinit var textViewPcCounting: TextView
    private lateinit var textViewKgCounting: TextView
    private lateinit var textViewCustomerName: TextView
    private lateinit var textViewOrderKg: TextView

    private lateinit var deliveryObject: Delivery
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delivery_denominations)

        val bundle = intent.extras
        val order: Order = bundle?.getSerializable("order") as Order

        CustomerFullDetails.instance.ordered = order
        CustomerFullDetails.instance.customer = CustomerList.getCustomerById(order.customerId)!!
        deliveryObject = Delivery(order.orderId,
            Delivery.getDeliveryId(order),
            CustomerFullDetails.instance.customer.id,
            CustomerFullDetails.instance.customer.name,
            "0",
            "0",
            arrayListOf(),
            order.pricePerKilo,
            order.previousDue,
            "0"
        )

        pcInput = findViewById(R.id.delivery_denomination_input_pc)
        kgInput = findViewById(R.id.delivery_denomination_input_kg)
        totalPc = findViewById(R.id.delivery_denomination_view_totalPc)
        totalKg = findViewById(R.id.delivery_denomination_view_totalKg)
        textViewPcCounting = findViewById(R.id.delivery_denomination_view_totalPc_counting)
        textViewKgCounting = findViewById(R.id.delivery_denomination_view_totalKg_counting)
        textViewCustomerName = findViewById(R.id.delivery_denomination_view_customerName)
        textViewOrderKg = findViewById(R.id.delivery_denomination_view_orderedKG)

        textViewCustomerName.text = CustomerFullDetails.instance.customer.name
        textViewOrderKg.text = CustomerFullDetails.instance.ordered.kilos

        pcInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                countingPcAndKg(deliveryObject.pc_kilo_denominations)
            }
        })

        kgInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                countingPcAndKg(deliveryObject.pc_kilo_denominations)
            }
        })
    }

    fun View.onClickAddToDenomination() {
        if(getKg().isEmpty())
        {
            Toast.makeText(this@DeliveryDenominations, "Enter KG", Toast.LENGTH_SHORT).show()
            return
        }
        var pc = getPc()
        if(pc.isEmpty())
            pc = "0"
        addDenomination(Denominations(pc, getKg()), deliveryObject.pc_kilo_denominations)
        resetKg()
        showDenominations(deliveryObject.pc_kilo_denominations)
    }

    private fun showDenominations(denominations: ArrayList<Denominations>) {
        val layout = findViewById<LinearLayout>(R.id.delivery_denomination_view_container)

        layout.removeAllViews()
        val ll1 = LinearLayout(applicationContext)
        ll1.orientation = LinearLayout.VERTICAL
        for (denomination in denominations) {
            val pc = denomination.pc
            val kg = denomination.kg
            val record = LinearLayout(applicationContext)
            record.orientation = LinearLayout.HORIZONTAL

            val fullLabel = TextView(applicationContext)
            val deleteButton = Button(applicationContext)
            deleteButton.setOnClickListener {
                setKg(kg)
                setPc(pc)
                removeDenomination(denominations, denomination)
                showDenominations(denominations)
                countingPcAndKg(denominations)
            }
            deleteButton.text = "Delete"
            fullLabel.text = "$pc - $kg"
            record.addView(fullLabel)
            record.addView(deleteButton)
            ll1.addView(record)
        }
        layout.addView(ll1)
        refreshTotalPcAndKg(denominations)
    }

    private fun getPc(): String {
        return pcInput.text.toString()
    }

    private fun getKg(): String {
        return kgInput.text.toString()
    }

    private fun resetKg() {
        kgInput.setText("")
    }

    private fun setKg(string: String) {
        kgInput.setText(string)
    }

    private fun setPc(string: String) {
        pcInput.setText(string)
    }

    fun countingPcAndKg(denominations: ArrayList<Denominations>) {
        var pc = try {
            pcInput.text.toString().toInt()
        } catch (e: Exception) {
            0
        }

        var kg = try {
            kgInput.text.toString().toFloat()
        } catch (e: Exception) {
            0.0F
        }

        for (denomination in denominations) {
            pc += denomination.pc.toInt()
            kg += denomination.kg.toFloat()
        }

        textViewPcCounting.text = pc.toString()
        textViewKgCounting.text = kg.toString()
    }

    private fun addDenomination(denomination: Denominations, denominations: ArrayList<Denominations>) {
        denominations.add(denomination)
        countingPcAndKg(denominations)
    }

    private fun removeDenomination(denominations: ArrayList<Denominations>, denomination: Denominations) {
        denominations.remove(denomination)
        countingPcAndKg(denominations)
    }

    private fun refreshTotalPcAndKg(denominations: ArrayList<Denominations>) {
        var sumPc = 0
        var sumKg = 0.0F
        for (denomination in denominations) {
            if(denomination.pc.isNotEmpty())
                sumPc += denomination.getPcInt()
            if(denomination.kg.isNotEmpty())
                sumKg += denomination.getKgFloat()
        }
        totalPc.text = sumPc.toString()
        totalKg.text = sumKg.toString()
    }

    fun onClickDeliveryDenominationSubmitBtn(view: View) {
        goToBillingActivity()
    }

    private fun goToBillingActivity() {
        val myIntent = Intent(this, BillingActivity::class.java)
        val bundle = Bundle()
        bundle.putSerializable("deliveryObject", deliveryObject)
        myIntent.putExtras(bundle)
        this.startActivity(myIntent)
    }
}