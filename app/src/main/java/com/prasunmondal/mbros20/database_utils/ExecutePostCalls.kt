package com.prasunmondal.mbros20.database_utils

import android.os.AsyncTask
import android.util.Log
import com.google.gson.GsonBuilder
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.*
import java.lang.reflect.Type
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.net.URLEncoder
import java.util.*
import java.util.function.Consumer
import javax.net.ssl.HttpsURLConnection


class ExecutePostCalls(
        var scriptUrl: URL,
        var postDataParams: JSONObject,
        var onCompletion: Consumer<String>
) :
    AsyncTask<String?, Void?, String>() {
    override fun onPreExecute() {}

    override fun doInBackground(vararg p0: String?): String? {
        try {
            val url = scriptUrl //Enter URL here
            val httpURLConnection = url.openConnection() as HttpURLConnection
            httpURLConnection.doOutput = true
            httpURLConnection.requestMethod = "POST" // here you are telling that it is a POST request, which can be changed into "PUT", "GET", "DELETE" etc.
            httpURLConnection.setRequestProperty("Content-Type", "application/json") // here you are setting the `Content-Type` for the data you are sending which is `application/json`
            httpURLConnection.connect()
            val jsonObject = JSONObject()
            jsonObject.put("para_1", "arg_1")
            val wr = DataOutputStream(httpURLConnection.outputStream)
            wr.writeBytes(jsonObject.toString())
            wr.flush()
            wr.close()
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return null
    }
//    override fun doInBackground(vararg p0: String?): String {
//        return try {
//            Log.e("params", postDataParams.toString())
//            val conn = scriptUrl.openConnection() as HttpURLConnection
//            conn.readTimeout = 15000
//            conn.connectTimeout = 15000
//            conn.requestMethod = "POST"
//            conn.doInput = true
//            conn.doOutput = true
//            val os = conn.outputStream
//            val writer = BufferedWriter(
//                    OutputStreamWriter(os, "UTF-8")
//            )
//            writer.write(getPostDataString(postDataParams))
//            writer.flush()
//            writer.close()
//            os.close()
//            val responseCode = conn.responseCode
//            if (responseCode == HttpsURLConnection.HTTP_OK) {
//                val `in` = BufferedReader(InputStreamReader(conn.inputStream))
//                val sb = StringBuffer("")
//                var line: String? = ""
//                while (`in`.readLine().also { line = it } != null) {
//                    sb.append(line)
//                    break
//                }
//                `in`.close()
//                sb.toString()
//            } else {
//                "false : $responseCode"
//            }
//        } catch (e: Exception) {
//            "Exception: " + e.message
//        }
//    }

    override fun onPostExecute(result: String) {
        Log.i("DBCall Result: ", result)
        if(onCompletion!=null)
            onCompletion.accept(result)
    }

    @Throws(Exception::class)
    fun getPostDataString(params: JSONObject): String {
        val result = StringBuilder()
        var first = true
        val itr = params.keys()
        while (itr.hasNext()) {
            val key = itr.next()
            val value = params[key]
            if (first) first = false else result.append("&")
            result.append(URLEncoder.encode(key, "UTF-8"))
            result.append("=")
            result.append(URLEncoder.encode(value.toString(), "UTF-8"))
        }
        return result.toString()
    }

    companion object {
        fun <T> parseJSONObject(type: Type?, p1: JSONObject?, arrayLabel: String?): ArrayList<T>? {
            if (p1 == null) {
                return null
            }
            var jsonarray: JSONArray? = null
            try {
                jsonarray = p1.getJSONArray(arrayLabel)
            } catch (e: Exception) {
                Log.e("parseJSONObject", "Error while parsing")
            }
            return GsonBuilder().create().fromJson(jsonarray.toString(), type)
        }
    }
}