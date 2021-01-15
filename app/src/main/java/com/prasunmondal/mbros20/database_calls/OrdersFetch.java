package com.prasunmondal.mbros20.database_calls;

import com.prasunmondal.mbros20.database_utils.FetchAllFromDB;
import com.prasunmondal.mbros20.models.Order;

import java.util.ArrayList;
import java.util.function.Consumer;

public class OrdersFetch {
    public static void execute(final Consumer<ArrayList<Order>> onCompletion) throws Exception
    {
        FetchAllFromDB sd = new FetchAllFromDB(
                DatabaseStrings.TABNAME_ORDER,jsonString -> parseAndExecute(jsonString, onCompletion));
        sd.execute();
    }

    private static void parseAndExecute(String jsonString, Consumer<ArrayList<Order>> onCompletion)
    {
        ArrayList<Order> order = new Order().parse(jsonString);
        onCompletion.accept(order);
    }
}