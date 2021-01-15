package com.prasunmondal.mbros20.database_utils;

import com.prasunmondal.mbros20.database_calls.DatabaseStrings;

import org.json.JSONObject;

import java.net.URL;
import java.util.function.Consumer;

public class FetchAllFromDB {
        Consumer<String> onCompletion;
        JSONObject postDataParams = new JSONObject();
        URL scriptUrl;

    public FetchAllFromDB(String tabName, Consumer<String> onCompletion) throws Exception {
        this.onCompletion = onCompletion;

        scriptUrl = new URL(DatabaseStrings.GOOGLE_SCRIPT_URL);

        postDataParams.put("opCode", "FETCH_ALL");
        postDataParams.put("sheetId", DatabaseStrings.SHEET_ID_PROD);
        postDataParams.put("tabName", tabName);
    }

    public void execute() {
        ExecutePostCalls c = new ExecutePostCalls(scriptUrl, postDataParams, onCompletion);
        c.execute();
    }

    }

