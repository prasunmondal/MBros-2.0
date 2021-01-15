package com.prasunmondal.mbros20.models

import com.google.gson.reflect.TypeToken
import com.prasunmondal.mbros20.utils.DataParser
import java.util.ArrayList

class Delivery {
    var orderId: String
    var deliveryId: String
    var customerId: String
    var customerName: String
    var pcs: String
    var kilos: String
    var pc_kilo_denominations: String
    var pricePerKilo: String
    var previousDue: String
    var todayPaid: String

    constructor(
        orderId: String,
        deliveryId: String,
        customerId: String,
        customerName: String,
        pcs: String,
        kilos: String,
        pc_kilo_denominations: String,
        pricePerKilo: String,
        previousDue: String,
        todayPaid: String
    ) {
        this.orderId = orderId
        this.deliveryId = deliveryId
        this.customerId = customerId
        this.customerName = customerName
        this.pcs = pcs
        this.kilos = kilos
        this.pc_kilo_denominations = pc_kilo_denominations
        this.pricePerKilo = pricePerKilo
        this.previousDue = previousDue
        this.todayPaid = todayPaid
    }

    companion object {
        public fun parse(jsonString: String): ArrayList<Delivery> {
            return DataParser.parseJSONObject(object :
                TypeToken<ArrayList<Delivery>>() {}.type, jsonString, "records")
        }
    }
}