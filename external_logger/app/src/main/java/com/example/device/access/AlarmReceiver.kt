package com.example.device.access

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.util.Log
import android.widget.Toast
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val ALW = AlarmLogWriter(context)
        Toast.makeText(context, "Alarm Start", Toast.LENGTH_SHORT).show()
        Log.d("Alarm Bell", "Alarm just fired")
        ALW.al_extLogger("Alarm Bell", "Alarm just fired")
        ALW.al_leaveLog()
    }
}


// Log Writer class for AlarmReceiver
class AlarmLogWriter(private var context: Context) {

    fun al_extLogger(tag: String, msg: String){
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

    fun al_leaveLog(){
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH) + 1
        val day = c.get(Calendar.DAY_OF_MONTH)

        val filename = "logfile_byAlarm$year$month$day.txt"
        val outputFile = File(context.externalCacheDir, filename)

        Runtime.getRuntime().exec("logcat -v threadtime -d *:W -f"+outputFile.absolutePath)

    }

}