package com.prasunmondal.mbros20.models

import com.google.gson.reflect.TypeToken
import com.prasunmondal.mbros20.activities.Denominations
import com.prasunmondal.mbros20.utils.DataParser
import java.io.Serializable
import java.lang.Exception
import java.util.ArrayList

class Delivery: Serializable {
    var orderId: String
    var deliveryId: String
    var deliveryDate: String
    var deliveryTime: String
    var customerId: String
    var customerName: String
    var pcs: String
    var kilos: String
    var pc_kilo_denominations: ArrayList<Denominations>
    var pricePerKilo: String
    var previousDue: String
    var todayPaid: String

    constructor(
        orderId: String,
        deliveryId: String,
        deliveryDate: String,
        deliveryTime: String,
        customerId: String,
        customerName: String,
        pcs: String,
        kilos: String,
        pc_kilo_denominations: ArrayList<Denominations>,
        pricePerKilo: String,
        previousDue: String,
        todayPaid: String
    ) {
        this.orderId = orderId
        this.deliveryId = deliveryId
        this.deliveryDate = deliveryDate
        this.deliveryTime = deliveryTime
        this.customerId = customerId
        this.customerName = customerName
        this.pcs = pcs
        this.kilos = kilos
        this.pc_kilo_denominations = pc_kilo_denominations
        this.pricePerKilo = pricePerKilo
        this.previousDue = previousDue
        this.todayPaid = todayPaid
    }

    override fun toString(): String {
        return "Delivery:: " +
                "\nOrder Id: $orderId" +
                "\ndeliveryId: $deliveryId" +
                "\ndeliveryDate: $deliveryDate" +
                "\ndeliveryTime: $deliveryTime" +
                "\ncustomerId: $customerId" +
                "\ncustomerName: $customerName" +
                "\npcs: $pcs" +
                "\nkilos: $kilos" +
                "\npc_kilo_denominations: $pc_kilo_denominations" +
                "\npricePerKilo: $pricePerKilo" +
                "\npreviousDue: $previousDue" +
                "\ntodayPaid: $todayPaid"
    }

    fun getKilosFloat(): Float {
        return kilos.toFloat()
    }

    fun getPcInt(): Int {
        return pcs.toInt()
    }

    companion object {
        fun parse(jsonString: String): ArrayList<Delivery> {
                return DataParser.parseJSONObject(object :
                        TypeToken<ArrayList<Delivery>>() {}.type, jsonString, "records")
        }

        fun getDeliveryId(order: Order): String{
            return "del-" + order.orderId;
        }
    }
}