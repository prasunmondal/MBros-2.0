package com.prasunmondal.mbros20.DatabaseUtils;

import com.prasunmondal.mbros20.DatabaseCalls.DatabaseStrings;
import com.prasunmondal.mbros20.DatabaseUtils.ExecutePostCalls;
import com.prasunmondal.mbros20.Utils.StringConstants;

import org.json.JSONObject;

import java.net.URL;
import java.util.function.Consumer;

public class FetchFromDB {
        Consumer<String> onCompletion;
        JSONObject postDataParams = new JSONObject();
        URL scriptUrl;

    public FetchFromDB(String keys, String tabName, String column, Consumer<String> onCompletion) throws Exception {
        this.onCompletion = onCompletion;

        scriptUrl = new URL(DatabaseStrings.GOOGLE_SCRIPT_URL);

        postDataParams.put("opCode", "FETCH");
        postDataParams.put("sheetId", DatabaseStrings.SHEET_ID_PROD);
        postDataParams.put("tabName", tabName);
        postDataParams.put("keys", keys);
        postDataParams.put("searchColumn", column);
    }

    public void execute() {
        ExecutePostCalls c = new ExecutePostCalls(scriptUrl, postDataParams, onCompletion);
        c.execute();
    }

    }

