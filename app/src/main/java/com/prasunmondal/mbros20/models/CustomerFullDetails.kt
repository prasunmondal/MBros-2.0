package com.prasunmondal.mbros20.models

import com.prasunmondal.mbros20.activities.Denominations
import java.util.ArrayList

class CustomerFullDetails {
    lateinit var customer: Customer
    lateinit var ordered: Order
    lateinit var delivery: Delivery
//
//    lateinit var customerId: String
//    lateinit var customerName: String
//    lateinit var phoneNumber1: String
//    lateinit var phoneNumber2: String
//    lateinit var address: String
//
//    lateinit var orderId: String
//    lateinit var orderedPieces: String
//    lateinit var orderedKilos: String
//    lateinit var pricePerKilo: String
//    lateinit var previousDue: String
//
//    lateinit var deliveryId: String
//    lateinit var deliveredPieces: String
//    lateinit var deliveredKilos: String
//    lateinit var deleivered_pc_kilo_denominations: ArrayList<Denominations>
//    lateinit var paidToday: String

    private constructor()
    companion object {
        var instance = CustomerFullDetails()
    }
}