package com.example.device.access

import android.content.Context
import android.os.Environment
import android.os.Handler
import java.io.DataOutputStream
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*


class externalLogWriter(private var context: Context) {
    var timer: Timer = Timer()
    private val mDelayHandler: Handler by lazy {
        Handler()
    }

    // append to external logfile 'customlogfileyymmdd.txt'
    fun extLogger(tag: String,content: String){
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH) + 1
        val day = c.get(Calendar.DAY_OF_MONTH)


        val formattedDate = SimpleDateFormat("yyyy.MM.dd HH:mm:ss",Locale.ENGLISH).format(c.timeInMillis)
        val logdata  = formattedDate + "\t" + tag + "\t" + content + "\n"


        val filename = "customlogfile$year$month$day.txt"
        val outputFile = File(context?.externalCacheDir, filename)
        outputFile.appendText(logdata)


    }

    //----------------------------------------------------------------------------------------------------------------
    fun leaveLog(){
        val c = Calendar.getInstance()

        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH) + 1
        val day = c.get(Calendar.DAY_OF_MONTH)

        val filename = "logfile$year$month$day.txt"
        val outputFile = File(context?.externalCacheDir, filename)

        Runtime.getRuntime().exec(  "logcat -v threadtime -d *:V -f"+outputFile.absolutePath)
        extLogger("custom", "custom external log")
        leaveLogTimer()
    }

    // recursive function running inside
    fun leaveLogTimer(){
        mDelayHandler.postDelayed(::leaveLog, 60000) // 1 min delay
    }
    //----------------------------------------------------------------------------------------------------------------
}