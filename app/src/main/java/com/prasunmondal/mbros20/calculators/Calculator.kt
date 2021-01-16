package com.prasunmondal.mbros20.calculators

import com.prasunmondal.mbros20.activities.Denominations
import com.prasunmondal.mbros20.models.Delivery
import com.prasunmondal.mbros20.models.Order
import java.lang.Exception

class Calculator {

    companion object {
        fun deliveryTotalKg(delivery: Delivery): Float {
            var kg = 0.0F

            delivery.pc_kilo_denominations.forEach { denomination ->
                kg += denomination.kg.toFloat()
            }
            return kg
        }

        fun deliveryTotalPc(delivery: Delivery): Int {
            var pc = 0

            delivery.pc_kilo_denominations.forEach { denomination ->
                pc += denomination.pc.toInt()
            }
            return pc
        }

        fun deliveryTotalPriceForTodaysDelivery(delivery: Delivery, order: Order): Float {
            return (order.pricePerKilo.toFloat() * deliveryTotalKg(delivery))
        }

        fun deliveryTotalAmount_today_prevDue(delivery: Delivery, order: Order): Float {
            return deliveryTotalPriceForTodaysDelivery(
                delivery,
                order
            ) + order.previousDue.toFloat()
        }

        fun deliveryRemainingAmount(delivery: Delivery, order: Order, paidAmount: Float): Float {
            return deliveryTotalAmount_today_prevDue(delivery, order) - paidAmount
        }
    }
}