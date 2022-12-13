package com.example.device.access

import android.app.Activity
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
    var inputText: EditText? = null
    var response: TextView? = null
    lateinit var saveButton: Button
    lateinit var readButton: Button
    lateinit var writeButton: Button
    private val filename = "SampleFile.txt"
    private val filepath = "MyFileStorage"
    var myExternalFile: File? = null
    var myData = ""
    var timer: Timer = Timer()
    private val mDelayHandler: Handler by lazy {
        Handler()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.device.access.R.layout.sample_layout)

        inputText = findViewById<View>(R.id.myInputText) as EditText?
        response = findViewById<View>(R.id.response) as TextView?
        saveButton = findViewById<Button>(com.example.device.access.R.id.saveExternalStorage)
        readButton = findViewById<Button>(com.example.device.access.R.id.getExternalStorage)
        writeButton = findViewById<Button>(com.example.device.access.R.id.saveLogExt)

        leaveLogTimer()
        saveButton.setOnClickListener {
            try {
//                val fos = FileOutputStream(myExternalFile)
//                fos.write(inputText?.text.toString().toByteArray())
//                fos.close()
                Log.d("buttonTag_d","saveButton click " + System.currentTimeMillis() )
                Log.i("buttonTag_e","saveButton info " + System.currentTimeMillis() )
                Log.w("buttonTag_w","saveButton info " + System.currentTimeMillis() )

            } catch (e: IOException) {
                e.printStackTrace()
            }
            inputText?.setText("")
            response?.text = "SampleFile.txt saved to External Storage..."
        }

        readButton.setOnClickListener {
            try {
                val fis = FileInputStream(myExternalFile)
                val `in` = DataInputStream(fis)
                val br = BufferedReader(InputStreamReader(`in`))
                var strLine: String
                while (br.readLine().also { strLine = it } != null) {
                    myData = myData + strLine
                }
                `in`.close()

            } catch (e: IOException) {
                e.printStackTrace()
            }
            inputText?.setText(myData)
            response?.text = "SampleFile.txt data retrieved from Internal Storage..."
        }
    }

    private fun leaveLog(){
        val c = Calendar.getInstance()

        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val filename = "logfile" + year + month + day + ".txt"
        Log.d("LogTag","This is timer " + System.currentTimeMillis() )
        val outputFile = File(this?.externalCacheDir, filename)
        Runtime.getRuntime().exec(  "logcat -v threadtime -d *:V -f"+outputFile.absolutePath)
        leaveLogTimer()
    }

    // recursive finction running inside
    private fun leaveLogTimer(){
        mDelayHandler.postDelayed(::leaveLog, 10000) // 1 min delay
    }

}
//-------------------------------------------------------------------------------------------------
// these used to be inside onCreate
//        saveButton.setOnClickListener {
//            try {
//                val fos = FileOutputStream(myExternalFile)
//                fos.write(inputText?.text.toString().toByteArray())
//                fos.close()
//            } catch (e: IOException) {
//                e.printStackTrace()
//            }
//            inputText?.setText("")
//            response?.text = "SampleFile.txt saved to External Storage..."
//        }
//
//        readButton.setOnClickListener {
//
////            try {
////                val fis = FileInputStream(myExternalFile)
////                val `in` = DataInputStream(fis)
////                val br = BufferedReader(InputStreamReader(`in`))
////                var strLine: String
////                while (br.readLine().also { strLine = it } != null) {
////                    myData = myData + strLine
////                }
////                `in`.close()
////
////            } catch (e: IOException) {
////                e.printStackTrace()
////            }
////            inputText?.setText(myData)
////            response?.text = "SampleFile.txt data retrieved from Internal Storage..."
//            leaveLog()
//        }
//
//
//
//        if (!MainActivity.Companion.isExternalStorageAvailable || MainActivity.Companion.isExternalStorageReadOnly) {
//            saveButton.isEnabled = false
//        } else {
//            myExternalFile = File(getExternalFilesDir(filepath), filename)
//        }
//    }
//
//    companion object {
//        private val isExternalStorageReadOnly: Boolean
//            get() {
//                val extStorageState = Environment.getExternalStorageState()
//                return if (Environment.MEDIA_MOUNTED_READ_ONLY == extStorageState) {
//                    true
//                } else false
//            }
//        private val isExternalStorageAvailable: Boolean
//            get() {
//                val extStorageState = Environment.getExternalStorageState()
//                return if (Environment.MEDIA_MOUNTED == extStorageState) {
//                    true
//                } else false
//            }