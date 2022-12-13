package com.example.device.access

import android.content.Context
import android.util.Log
import java.io.File
import java.util.*

class externalLogWriter(private var context: Context) {
    fun extLogger(tag: String,content: String){
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val filename = "logfile" + year + month + day + ".txt"
        val outputFile = File(context?.externalCacheDir, filename)
    }
}