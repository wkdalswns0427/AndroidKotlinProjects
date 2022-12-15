package com.example.device.access

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

// Alarm feature setup
// This class is called when button is clicked
class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val ALW = AlarmLogWriter(context)
        Toast.makeText(context, "Alarm Start", Toast.LENGTH_SHORT).show()
        Log.d("Alarm Bell", "Alarm just fired")
        ALW.al_customLogger("Alarm Bell", "Alarm just fired")
        ALW.al_leaveLog()
//        ALW.al_deletePrev()
    }
}


// Log Writer class for AlarmReceiver
class AlarmLogWriter(private var context: Context) {

    // custom logging in alarm
    fun al_customLogger(tag: String, msg: String){
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH) + 1
        val day = c.get(Calendar.DAY_OF_MONTH)


        val formattedDate = SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.ENGLISH).format(c.timeInMillis)
        val logdata  = formattedDate + "\t" + tag + "\t" + msg + "\n"


        val filename = "customlog_byAlarm$year$month$day.txt"
        val outputFile = File(context.externalCacheDir, filename)
        outputFile.appendText(logdata)

    }

    // update logcat file "logfile_byAlarm$yyyyMMdd.txt"
    fun al_leaveLog(){
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH) + 1
        val day = c.get(Calendar.DAY_OF_MONTH)

        val filename = "logfile_byAlarm$year$month$day.txt"
        val outputFile = File(context.externalCacheDir, filename)

        Runtime.getRuntime().exec("logcat -v threadtime -d *:W -f"+outputFile.absolutePath)

    }

    fun al_deletePrev() {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        var month = c.get(Calendar.MONTH) + 1
        val day = c.get(Calendar.DAY_OF_MONTH)

        if(month!=1){
            month -= 1
        }else{
            month = 12
        }

        val prevfilename = "logfile_byAlarm$year$month$day.txt"
        val targetFIle = File(context.externalCacheDir, prevfilename)

        if (!targetFIle.exists()) {
            return
        } else {
            targetFIle.delete()
            Toast.makeText(context, "Old file deleted", Toast.LENGTH_SHORT).show()
        }
    }
}
