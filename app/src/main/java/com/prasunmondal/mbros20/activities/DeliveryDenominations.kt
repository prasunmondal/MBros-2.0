package com.prasunmondal.mbros20.activities

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.prasunmondal.mbros20.R

class Denominations {
    var pc: String
    var kg: String

    constructor(pc: String, kg: String) {
        this.pc = pc
        this.kg = kg
    }

    fun getPcInt(): Int {
        return pc.toInt()
    }

    fun getKgInt(): Int {
        return kg.toInt()
    }
}
class DeliveryDenominations : AppCompatActivity() {
    lateinit var denominationMap: ArrayList<Denominations>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delivery_denominations)

        denominationMap = arrayListOf()
    }

    fun onClickAddToDenomination(view: View) {
        denominationMap.add(Denominations(getPc(), getKg()))
        showDenominations(denominationMap)
    }

    private fun showDenominations(denominations: ArrayList<Denominations>) {
        val layout = findViewById<LinearLayout>(R.id.delivery_denomination_view_container)

        for (denomination in denominations) {
            val fullLabel = TextView(applicationContext)
            fullLabel.text = "$denomination.pc - $denomination.kg"
            layout.addView(fullLabel)
        }
        refreshTotalPcAndKg(denominations)
    }

    private fun getPc(): String {
        return findViewById<EditText>(R.id.delivery_denomination_input_pc).text.toString()
    }

    private fun getKg(): String {
        return findViewById<EditText>(R.id.delivery_denomination_input_kg).text.toString()
    }

    private fun refreshTotalPcAndKg(denominations: ArrayList<Denominations>) {
        var totalPc = findViewById<TextView>(R.id.delivery_denomination_view_totalPc)
        var totalKg = findViewById<TextView>(R.id.delivery_denomination_view_totalKg)

        var sumPc = 0
        var sumKg = 0;
        for (denomination in denominations) {
            if(denomination.pc.length != 0)
                sumPc += denomination.getPcInt()
            if(denomination.kg.length != 0)
                sumKg += denomination.getKgInt()
        }
        totalPc.text = sumPc.toString()
        totalKg.text = sumKg.toString()
    }
}