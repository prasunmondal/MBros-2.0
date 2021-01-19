package com.prasunmondal.mbros20.database_utils;

import com.prasunmondal.mbros20.database_calls.DatabaseStrings;

import org.json.JSONObject;

import java.net.URL;
import java.util.function.Consumer;

public class UpdateDataInDB
{
    Consumer<String> onCompletion;
    JSONObject postDataParams = new JSONObject();
    URL scriptUrl;

    public UpdateDataInDB(String newValue, String tabName, String uniqueValue, int uniqueColumn, Consumer<String> onCompletion, String json2) throws Exception
    {
        this.onCompletion = onCompletion;

        scriptUrl = new URL(DatabaseStrings.GOOGLE_SCRIPT_URL);
        String sheetId = DatabaseStrings.SHEET_ID_PROD;

        postDataParams.put("data", newValue);
        postDataParams.put("sheetId", sheetId);
        postDataParams.put("tabName", tabName);
        postDataParams.put("opCode",  "UPDATE");
        postDataParams.put("dataValue", uniqueValue);
        postDataParams.put("dataColumn", json2);
    }

    public void execute()
    {
        ExecutePostCalls c=new ExecutePostCalls(scriptUrl,postDataParams,onCompletion);
        c.execute();
    }

}
