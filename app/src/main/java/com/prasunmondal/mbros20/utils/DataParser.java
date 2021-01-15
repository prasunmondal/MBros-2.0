package com.prasunmondal.mbros20.utils;

import android.util.Log;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class DataParser {
    public static <T> ArrayList<T> parseJSONObject(Type type, String jsonString, String arrayLabel) {//throws Exception {
        System.err.println("============================== Parsing Data log ==============================");
        System.err.println(jsonString);
        System.err.println();

        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(jsonString).getAsJsonObject();

        if (jsonObject == null) {
            return null;
        }
        JsonArray jsonarray = null;
        try {
            jsonarray = jsonObject.getAsJsonArray(arrayLabel);
        } catch (Exception e) {
            Log.e("parseJSONObject", "Error while parsing");
        }
        ArrayList<T> result = new GsonBuilder().create().fromJson(jsonarray.toString(), type);
        if(result.size() == 0) {
//            throw new NoRecordFoundException();
        }
        return result;
    }
}
