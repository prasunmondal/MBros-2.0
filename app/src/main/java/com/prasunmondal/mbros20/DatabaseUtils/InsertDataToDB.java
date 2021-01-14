package com.prasunmondal.mbros20.DatabaseUtils;

import com.prasunmondal.mbros20.DatabaseCalls.DatabaseStrings;
import com.prasunmondal.mbros20.DatabaseUtils.ExecutePostCalls;

import org.json.JSONObject;

import java.net.URL;
import java.util.function.Consumer;

public class InsertDataToDB
{
    Consumer<String> onCompletion;
    JSONObject postDataParams = new JSONObject();
    URL scriptUrl;

    public InsertDataToDB(String data, String tabName, Consumer<String> onCompletion) throws Exception
    {
        this.onCompletion = onCompletion;

        scriptUrl = new URL(DatabaseStrings.GOOGLE_SCRIPT_URL);
        String sheetId = DatabaseStrings.SHEET_ID_PROD;

        postDataParams.put("data", data);
        postDataParams.put("sheetId", sheetId);
        postDataParams.put("tabName", tabName);
        postDataParams.put("opCode", "INSERT_UNIQUE");
    }

    public void execute()
    {
        ExecutePostCalls c=new ExecutePostCalls(scriptUrl,postDataParams,onCompletion);
        c.execute();
    }

}
