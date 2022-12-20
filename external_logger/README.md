# Android System Logger

### External Logger Class
copy and paste ExternalLogWriter.kt file to any application

ExternalLogWriter::extLogger(tag: String, msg: String)
- tag : log tag
- msg : message to log
- leaves log at "sdcard/Android/data/com.example.device.access/cache" as "customlogfile$yyyyMMdd.txt"
- in format of "DATE TIME TAG MESSAGE"
- Example
```
2022.12.13 20:26:17	Alarm Bell	Alarm just fired
```

### Logger application
- ** AlarmLogWriter::al_leaveLog() **
- leaves logcat data at "sdcard/Android/data/com.example.device.access/cache" as "logfile_byAlarm$yyyyMMdd.txt"
- automatically start alarmmanager, logging occurs twice a day
- delete old file everyday(-15d)
- custom alarm can be set together with ** AlarmLogWriter::al_customLogger **
