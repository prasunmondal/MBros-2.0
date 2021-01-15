package com.prasunmondal.mbros20.database_calls;

import com.prasunmondal.mbros20.database_utils.UpdateDataInDB;
import com.prasunmondal.mbros20.utils.StringConstants;
import com.prasunmondal.mbros20.models.Delivery;

import java.util.ArrayList;
import java.util.function.Consumer;

public class DeliveryRecordAdd
{
    public void execute(Delivery delivery, Consumer<ArrayList<Delivery>> onCompletion) throws Exception
    {
        UpdateDataInDB sd = new UpdateDataInDB(
                getStringToSave(delivery),
                DatabaseStrings.TABNAME_DELIVERY,
                delivery.getOrderId(),
                0,
                jsonString -> parseAndExecute(jsonString, onCompletion));
        sd.execute();
    }

    private static String getStringToSave(Delivery order) {
        return order.getOrderId() + StringConstants.SEPARATOR_DB
                + order.getDeliveryId() + StringConstants.SEPARATOR_DB
                + order.getCustomerId() + StringConstants.SEPARATOR_DB
                + order.getCustomerName() + StringConstants.SEPARATOR_DB
                + order.getPcs() + StringConstants.SEPARATOR_DB
                + order.getKilos() + StringConstants.SEPARATOR_DB
                + order.getPc_kilo_denominations() + StringConstants.SEPARATOR_DB
                + order.getPricePerKilo() + StringConstants.SEPARATOR_DB
                + order.getPreviousDue() + StringConstants.SEPARATOR_DB
                + order.getTodayPaid();
    }

    private static void parseAndExecute(String jsonString, Consumer<ArrayList<Delivery>> onCompletion)
    {
        ArrayList<Delivery> delivery = Delivery.Companion.parse(jsonString);
        onCompletion.accept(delivery);
    }
}
