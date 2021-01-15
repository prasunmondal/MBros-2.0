package com.prasunmondal.mbros20.database_utils;

import com.prasunmondal.mbros20.database_calls.DatabaseStrings;

import org.json.JSONObject;

import java.net.URL;
import java.util.function.Consumer;

public class InsertUniqueDataToDB
{
    Consumer<String> onCompletion;
    JSONObject postDataParams = new JSONObject();
    URL scriptUrl;

    public InsertUniqueDataToDB(String data, String tabName, String uniqueCol, Consumer<String> onCompletion) throws Exception
    {
        this.onCompletion = onCompletion;

        scriptUrl = new URL(DatabaseStrings.GOOGLE_SCRIPT_URL);
        String sheetId = DatabaseStrings.SHEET_ID_PROD;

        postDataParams.put("data", data);
        postDataParams.put("sheetId", sheetId);
        postDataParams.put("tabName", tabName);
        postDataParams.put("opCode", "INSERT_UNIQUE");
        postDataParams.put("uniqueCol", uniqueCol);
    }

    public void execute()
    {
        ExecutePostCalls c=new ExecutePostCalls(scriptUrl,postDataParams,onCompletion);
        c.execute();
    }

}

