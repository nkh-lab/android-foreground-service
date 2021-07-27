package com.nkhlab.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Simple foreground service with working thread
 */
public class SimpleForegroundService extends Service {
    private static final String TAG = "SimpleForegroundService";
    private static final int WORKING_THREAD_SLEEP_MS = 1000;
    private Thread mWorkingThread;
    private int mWorkingThreadCounter = 0;
    private AtomicBoolean mStopWorkingThread;

    public SimpleForegroundService() {
        Log.d(TAG, "SimpleForegroundService()");
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand()");

        final String CHANNEL_ID = "SimpleForegroundService_channel_01";
        final int NOTIFICATION_ID = 1;

        NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                "SimpleForegroundService",
                NotificationManager.IMPORTANCE_DEFAULT);

        ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).createNotificationChannel(channel);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("")
                .setContentText("").build();

        startForeground(NOTIFICATION_ID, notification);

        startWorkingThread();

        return START_STICKY;
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate()");
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy()");
        super.onDestroy();

        stopWorkingThread();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind()");
        return null;
    }

    private void startWorkingThread() {
        Log.d(TAG, "startWorkingThread()");

        mStopWorkingThread = new AtomicBoolean();
        mStopWorkingThread.set(false);
        mWorkingThread = new Thread(() -> {
            for (; ; ) {

                if (mStopWorkingThread.get()) break;

                ++mWorkingThreadCounter;

                Log.d(TAG, "mWorkingThreadCounter: " + mWorkingThreadCounter);

                try {
                    Thread.sleep(WORKING_THREAD_SLEEP_MS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        mWorkingThread.start();
    }

    private void stopWorkingThread() {
        Log.d(TAG, "stopWorkingThread()");

        mStopWorkingThread.set(true);
        try {
            mWorkingThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}