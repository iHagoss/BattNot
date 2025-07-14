package com.hagos.battnot.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

import com.hagos.battnot.util.NotificationUtils;

//Updates immediately, or after 1 minute
public class BatteryService extends Service {
    public final static IntentFilter UPDATE_FILTER = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
    private final static String TAG = "BatteryService";
    private final BroadcastReceiver bbr = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            Log.v(TAG, "Updating BattNot in foreground");
            NotificationUtils.updateBattNot(context, intent);
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(TAG, "Starting Foreground Service");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //TODO this throws ForegroundServiceStartNotAllowedException on some devices because of battery optimization

        this.startForeground(NotificationUtils.NOTIFICATION_ID,
                NotificationUtils.makeBattNot(this,
                        this.registerReceiver(bbr, UPDATE_FILTER)));

        return Service.START_STICKY;
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onDestroy() {
        try {
            this.unregisterReceiver(bbr);
        } catch (IllegalArgumentException e) {
            //
        }
    }
}
