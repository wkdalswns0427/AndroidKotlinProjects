package com.example.device.access

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.io.*
import java.util.*

//input saver
//-------------------------------------------------------------------------------------------------
class MainActivity : Activity() {

    private val mDelayHandler: Handler by lazy {
        Handler()
    }
    val ext = externalLogWriter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.device.access.R.layout.sample_layout)


        ext.leaveLogTimer()

    }
}
