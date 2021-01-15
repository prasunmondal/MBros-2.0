package com.prasunmondal.mbros20.database_calls;

import com.prasunmondal.mbros20.database_utils.InsertUniqueDataToDB;
import com.prasunmondal.mbros20.models.Customer;
import com.prasunmondal.mbros20.utils.StringConstants;

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
