package com.prasunmondal.mbros20.utils

import android.content.Context
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class FileUtils {
    companion object {
        fun write(context: Context, fileName: String, objectToWrite: Any) {
            val fos: FileOutputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE)
            val os = ObjectOutputStream(fos)
            os.writeObject(objectToWrite)
            os.close()
            fos.close()
        }

        fun <T> read(context: Context, fileName: String): T {
            val fis: FileInputStream = context.openFileInput(fileName)
            val inputStream = ObjectInputStream(fis)
            val readObject = inputStream.readObject() as T
            inputStream.close()
            fis.close()
            return readObject
        }

        fun isFilePresent(context: Context, fileName: String) {

        }

        fun removeFile(context: Context, filename: String) {
            context.deleteFile(filename)
        }
    }
}