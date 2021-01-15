package com.prasunmondal.mbros20.database_calls;

import com.prasunmondal.mbros20.database_utils.FetchAllFromDB;
import com.prasunmondal.mbros20.models.Customer;

import java.util.ArrayList;
import java.util.function.Consumer;

public class CustomersFetchAll {
    public static void execute(final Consumer<ArrayList<Customer>> onCompletion) throws Exception
    {
        FetchAllFromDB sd = new FetchAllFromDB(
                DatabaseStrings.TABNAME_REGISTER_CUSTOMER,jsonString -> parseAndExecute(jsonString, onCompletion));
        sd.execute();
    }

    private static void parseAndExecute(String jsonString, Consumer<ArrayList<Customer>> onCompletion)
    {
        ArrayList<Customer> customer = Customer.Companion.parse(jsonString);
        onCompletion.accept(customer);
    }
}
