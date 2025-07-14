package com.hagos.battnot.work;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;

import com.hagos.battnot.receiver.BatteryBroadcastReceiver;
import com.hagos.battnot.util.NotificationUtils;

import androidx.core.content.ContextCompat;

public class BatteryWorkManager {
    private final static int ONE_MIN = 60000, REQUEST_CODE = 123;

    public static void setRepeatingAlarm(Context context) {
        AlarmManager alarmManager = ContextCompat.getSystemService(context, AlarmManager.class);

        if (alarmManager != null) {
            stopRepeatingAlarm(context);

            alarmManager.setInexactRepeating(
                    AlarmManager.ELAPSED_REALTIME,
                    SystemClock.elapsedRealtime() + ONE_MIN,
                    ONE_MIN,
                    getPendingIntent(context));
        }
    }

    public static void stopRepeatingAlarm(Context context) {
        AlarmManager alarmManager = ContextCompat.getSystemService(context, AlarmManager.class);

        if (alarmManager != null) {
            alarmManager.cancel(getPendingIntent(context));
        }

    }

    private static PendingIntent getPendingIntent(Context context) {
        return PendingIntent.getBroadcast(
                context,
                REQUEST_CODE,
                new Intent(context, BatteryBroadcastReceiver.class)
                        .setAction(BatteryBroadcastReceiver.ACTION_UPDATE),
                NotificationUtils.FLAG_IMMUTABLE | PendingIntent.FLAG_CANCEL_CURRENT);
    }
}
