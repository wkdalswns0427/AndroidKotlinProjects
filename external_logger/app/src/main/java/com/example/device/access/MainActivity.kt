package com.example.device.access

import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import java.util.*

//input saver
//-------------------------------------------------------------------------------------------------
class MainActivity : Activity() {
    lateinit var btnSetAlarm: Button
    val ext = ExternalLogWriter(this)
    val ARec = AlarmReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.device.access.R.layout.sample_layout)
        setAlarm()

//        btnSetAlarm = findViewById(R.id.toggleButton01)
//        btnSetAlarm.setOnClickListener{
//            setAlarm()
//        }
    }

    private fun setAlarm() {
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, ARec::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)

        val calendar: Calendar = Calendar.getInstance()
        calendar.set(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH),
            calendar.get(Calendar.HOUR_OF_DAY),
            15,
            0
        )
        alarmManager.setRepeating(
            AlarmManager.RTC,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_HALF_DAY,
            pendingIntent
        )
        Toast.makeText(this, "AlarmButton is set", Toast.LENGTH_SHORT).show()
        Log.d("Alarm Bell", "Alarm just initiated")
        ext.extLogger("Alarm Bell", "Alarm just initiated")
    }
}
