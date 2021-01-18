package com.prasunmondal.mbros20.database_calls;

import com.prasunmondal.mbros20.database_utils.UpdateDataInDB;
import com.prasunmondal.mbros20.utils.StringConstants;
import com.prasunmondal.mbros20.models.Delivery;

import java.util.function.Consumer;

public class DeliveryRecordAdd
{
    public void execute(Delivery delivery, Consumer<String> onSuccess, Consumer<String> onFailure) throws Exception
    {
        UpdateDataInDB sd = new UpdateDataInDB(
                getStringToSave(delivery),
                DatabaseStrings.TABNAME_DELIVERY,
                delivery.getOrderId(),
                0,
                jsonString -> onComplete(jsonString, onSuccess, onFailure));
        sd.execute();
    }

    private static String getStringToSave(Delivery delivery) {
        return delivery.getOrderId() + StringConstants.SEPARATOR_DB
                + delivery.getDeliveryId() + StringConstants.SEPARATOR_DB
                + delivery.getDeliveryDate() + StringConstants.SEPARATOR_DB
                + delivery.getDeliveryTime() + StringConstants.SEPARATOR_DB
                + delivery.getCustomerId() + StringConstants.SEPARATOR_DB
                + delivery.getCustomerName() + StringConstants.SEPARATOR_DB
                + delivery.getPcs() + StringConstants.SEPARATOR_DB
                + delivery.getKilos() + StringConstants.SEPARATOR_DB
                + delivery.getPc_kilo_denominations() + StringConstants.SEPARATOR_DB
                + delivery.getPricePerKilo() + StringConstants.SEPARATOR_DB
                + delivery.getPreviousDue() + StringConstants.SEPARATOR_DB
                + delivery.getTodayPaid();
    }

    private static void onComplete(String jsonString, Consumer<String> onSuccess, Consumer<String> onFailure)
    {
        if(jsonString.contains("SUCCESS")) {
            onSuccess.accept(jsonString);
        } else {
            onFailure.accept(jsonString);
        }
    }
}
