package com.prasunmondal.mbros20.DatabaseCalls;

import com.prasunmondal.mbros20.DatabaseUtils.InsertUniqueDataToDB;
import com.prasunmondal.mbros20.models.Customer;
import com.prasunmondal.mbros20.Utils.StringConstants;

import java.util.function.Consumer;

public class CustomerAddNew
{
    Consumer<String> onCompletion;

    public CustomerAddNew(Consumer<String> onCompletion) {
        this.onCompletion = onCompletion;
    }

    public void execute(Customer customer) throws Exception
    {
        InsertUniqueDataToDB sd = new InsertUniqueDataToDB(
                getStringToSave(customer),
                DatabaseStrings.TABNAME_REGISTER_CUSTOMER,
                DatabaseStrings.CONSTRAINTS_REGISTER_CUSTOMER_UNIQUE_COLUMNS, onCompletion);
        sd.execute();
    }

    private static String getStringToSave(Customer customer) {
        return customer.getId() + StringConstants.SEPARATOR_DB
                + customer.getName() + StringConstants.SEPARATOR_DB
                + customer.getPhoneNumber1();
    }
}
