# android-foreground-service

Example of simple foreground service with working thread and without any activity.

## Start/Stop via adb shell
### Start service
```
$ am start-foreground-service com.nkhlab/.service.SimpleForegroundService
Starting service: Intent { act=android.intent.action.MAIN cat=[android.intent.category.LAUNCHER] cmp=com.nkhlab/.service.SimpleForegroundService }
```

### Stop service
```
generic_x86_arm:/ $ am stopservice com.nkhlab/.service.SimpleForegroundService
Stopping service: Intent { act=android.intent.action.MAIN cat=[android.intent.category.LAUNCHER] cmp=com.nkhlab/.service.SimpleForegroundService }
Service stopped
```

## Logcat output example
```
2021-07-27 12:57:54.384 5565-5565/com.nkhlab D/SimpleForegroundService: SimpleForegroundService()
2021-07-27 12:57:54.384 5565-5565/com.nkhlab D/SimpleForegroundService: onCreate()
2021-07-27 12:57:54.384 5565-5565/com.nkhlab D/SimpleForegroundService: onStartCommand()
2021-07-27 12:57:54.390 5565-5565/com.nkhlab D/SimpleForegroundService: startWorkingThread()
2021-07-27 12:57:54.392 5565-5587/com.nkhlab D/SimpleForegroundService: mWorkingThreadCounter: 1
2021-07-27 12:57:55.392 5565-5587/com.nkhlab D/SimpleForegroundService: mWorkingThreadCounter: 2
2021-07-27 12:57:56.393 5565-5587/com.nkhlab D/SimpleForegroundService: mWorkingThreadCounter: 3
2021-07-27 12:57:57.393 5565-5587/com.nkhlab D/SimpleForegroundService: mWorkingThreadCounter: 4
2021-07-27 12:57:58.394 5565-5587/com.nkhlab D/SimpleForegroundService: mWorkingThreadCounter: 5
2021-07-27 12:57:59.394 5565-5587/com.nkhlab D/SimpleForegroundService: mWorkingThreadCounter: 6
2021-07-27 12:58:00.395 5565-5587/com.nkhlab D/SimpleForegroundService: mWorkingThreadCounter: 7
2021-07-27 12:58:01.396 5565-5587/com.nkhlab D/SimpleForegroundService: mWorkingThreadCounter: 8
2021-07-27 12:58:02.246 5565-5565/com.nkhlab D/SimpleForegroundService: onDestroy()
2021-07-27 12:58:02.246 5565-5565/com.nkhlab D/SimpleForegroundService: stopWorkingThread()
```