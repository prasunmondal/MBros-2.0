package com.prasunmondal.mbros20.utils

import android.util.Log
import com.prasunmondal.mbros20.utils.logger.Bytes

class Applog {
    companion object {

        fun startMethod(throwable: Throwable) {
            info("Start Method", throwable)
        }

        fun endMethod(throwable: Throwable) {
            info("End Method", throwable)
        }

        fun info(variableName: String, value: Any?, throwable: Throwable) {
            var valueString = value.toString()
            if (value is ByteArray) {
                valueString = Bytes.printBytes(value)
            }
            Log.i("APPLOG: ", createString(variableName, valueString, throwable))
        }

        fun error(variableName: String, value: Any?, throwable: Throwable) {
            var valueString = value.toString()
            if (value is ByteArray) {
                valueString = Bytes.printBytes(value)
            }
            Log.e("APPLOG: ", createString(variableName, valueString, throwable))
        }

        fun error(string: String, throwable: Throwable) {
            error("", string, throwable)
        }

        fun info(string: String, throwable: Throwable) {
            info("", string, throwable)
        }

        private fun createString(
            variableName: String,
            string: String,
            throwable: Throwable
        ): String {
            return "${className(throwable)}.${methodName(throwable)}().$variableName: $string"
        }

        private fun className(throwable: Throwable): String {
            return throwable
                .stackTrace[0]
                .className
        }

        private fun methodName(throwable: Throwable): String {
            return throwable
                .stackTrace[0]
                .methodName
        }
    }
}
