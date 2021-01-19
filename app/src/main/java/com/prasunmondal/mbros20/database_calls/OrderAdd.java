package com.prasunmondal.mbros20.database_calls;

import com.prasunmondal.mbros20.database_utils.UpdateDataInDB;
import com.prasunmondal.mbros20.models.Order;
import com.prasunmondal.mbros20.utils.StringConstants;

import org.json.JSONObject;

import java.util.function.Consumer;

public class OrderAdd
{
    public void execute(Order order, Consumer<String> onCompletion, String json) throws Exception
    {
        UpdateDataInDB sd = new UpdateDataInDB(
                getStringToSave(order),
                DatabaseStrings.TABNAME_ORDER,
                order.customerId,
                0,
                onCompletion, json);
        sd.execute();
    }

    private static String getStringToSave(Order order) {
        return order.orderId + StringConstants.SEPARATOR_DB
                + order.customerId + StringConstants.SEPARATOR_DB
                + order.customerName + StringConstants.SEPARATOR_DB
                + order.pcs + StringConstants.SEPARATOR_DB
                + order.kilos + StringConstants.SEPARATOR_DB
                + order.pricePerKilo + StringConstants.SEPARATOR_DB
                + order.previousDue;
    }
}