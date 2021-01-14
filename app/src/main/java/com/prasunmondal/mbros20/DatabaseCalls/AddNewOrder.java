package com.prasunmondal.mbros20.DatabaseCalls;

import com.prasunmondal.mbros20.DatabaseUtils.UpdateDataInDB;
import com.prasunmondal.mbros20.models.Order;
import com.prasunmondal.mbros20.Utils.StringConstants;

import java.util.function.Consumer;

public class AddNewOrder
{
    public void execute(Order order, Consumer<String> onCompletion) throws Exception
    {
        UpdateDataInDB sd = new UpdateDataInDB(
                getStringToSave(order),
                DatabaseStrings.TABNAME_ORDER,
                order.customerId,
                0,
                onCompletion);
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
