package com.prasunmondal.mbros20.DatabaseCalls;

import com.prasunmondal.mbros20.DatabaseUtils.FetchAllFromDB;
import com.prasunmondal.mbros20.models.Customer;

import java.util.ArrayList;
import java.util.function.Consumer;

public class FetchAllCustomers {
    public static void execute(final Consumer<ArrayList<Customer>> onCompletion) throws Exception
    {
        FetchAllFromDB sd = new FetchAllFromDB(
                DatabaseStrings.TABNAME_REGISTER_CUSTOMER,p1 -> parseAndExecute(p1, onCompletion));
        sd.execute();
    }

    private static void parseAndExecute(String jsonString, Consumer<ArrayList<Customer>> onCompletion)
    {
        ArrayList<Customer> customer = Customer.Companion.parse(jsonString);
        onCompletion.accept(customer);
    }
}
