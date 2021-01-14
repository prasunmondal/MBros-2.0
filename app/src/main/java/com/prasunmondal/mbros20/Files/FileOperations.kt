package com.prasunmondal.mbros20.Files

import android.content.Context
import java.io.File
import java.io.ObjectInputStream
import java.io.ObjectOutputStream


class FileOperations {
    companion object {
    fun WriteObjectToFile(context: Context, fileName: String?, `object`: Any?) {
        try {
            val fos = context.openFileOutput(fileName, Context.MODE_PRIVATE)
            val os = ObjectOutputStream(fos)
            os.writeObject(`object`)
            os.close()
            fos.close()
        } catch (e: Exception) {
            println("Error while writing object to file")
            println(e)
        }
    }

    fun ReadObjectFromFile(context: Context, fileName: String?): Any? {
        try {
            val fis = context.openFileInput(fileName)
            val `is` = ObjectInputStream(fis)
            val `object` = `is`.readObject()
            `is`.close()
            fis.close()
            return `object`
        } catch (e: Exception) {
            println("Error while reading object from file")
            println(e)
        }
        return null
    }


        fun deleteObjectFromFile(fileName: String) {
            var fileName = fileName
            try {
//            File file = new File(fileName);
//            System.out.println("Delete Detaisl: ");
//            System.out.println(file.getAbsolutePath());
//            System.out.println(file.exists());
//            file.delete();
//            System.err.println("Delete Success: ");
                fileName = "/$fileName"
                println(fileName)
                val fdelete = File(fileName)
                if (fdelete.exists()) {
                    if (fdelete.delete()) {
                        println("file Deleted :$fileName")
                    } else {
                        println("file not Deleted :$fileName")
                    }
                } else {
                    println("file Deleted not exist:")
                }
            } catch (e: Exception) {
                System.err.println("Delete error: ")
                e.printStackTrace()
            }
        }
    }
}

