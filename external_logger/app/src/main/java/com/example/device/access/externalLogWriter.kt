package com.example.device.access

import android.content.Context
import android.os.Handler
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


// custom log writer
// call to leave log on "customlog_byAlarm$yyyyMMdd.txt"
class ExternalLogWriter(private var context: Context) {

    fun extLogger(tag: String, msg: String){
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH) + 1
        val day = c.get(Calendar.DAY_OF_MONTH)


        val formattedDate = SimpleDateFormat("yyyy.MM.dd HH:mm:ss",Locale.ENGLISH).format(c.timeInMillis)
        val logdata  = formattedDate + "\t" + tag + "\t" + msg + "\n"


        val filename = "customlogfile$year$month$day.txt"
        val outputFile = File(context.externalCacheDir, filename)
        outputFile.appendText(logdata)

    }

}