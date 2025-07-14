package com.hagos.battnot.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import com.hagos.battnot.R;
import com.hagos.battnot.service.BatteryService;
import com.hagos.battnot.util.NotificationUtils;

public class BatteryBroadcastReceiver extends BroadcastReceiver {
    public final static String ACTION_UPDATE = "com.hagos.battnot.UPDATE_ACTION";
    private final static String TAG = "BatBroadcastReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() != null && isNotificationEnabled(context)) {
            switch (intent.getAction()) {
                case ACTION_UPDATE:
                case Intent.ACTION_POWER_CONNECTED:
                case Intent.ACTION_POWER_DISCONNECTED:
                    Log.v(TAG, "Updating BattNot in background");
                    NotificationUtils.updateBattNot(context);

                    break;
                case Intent.ACTION_MY_PACKAGE_REPLACED:
                case Intent.ACTION_BOOT_COMPLETED:
                    Toast.makeText(context, context.getString(R.string.message_starting), Toast.LENGTH_SHORT).show();

                    NotificationUtils.startBattNot(context);

                    if (context.getSharedPreferences(context.getString(R.string.preference_filename), Context.MODE_PRIVATE)
                            .getBoolean(context.getString(R.string.preference_immediate), false)) {
                        if (Build.VERSION.SDK_INT >= 26) {
                            context.startForegroundService(new Intent(context, BatteryService.class));
                        } else {
                            context.startService(new Intent(context, BatteryService.class));
                        }
                    }
                    break;
                default:
                    Log.e(TAG, context.getString(R.string.message_received_strange_intent, intent.getAction()));
            }
        }
    }

    private boolean isNotificationEnabled(Context context) {
        return context.getSharedPreferences(context.getString(R.string.preference_filename), Context.MODE_PRIVATE).getBoolean(context.getString(R.string.preference_notification), false) &&
                NotificationUtils.canShowNotifications(context);
    }
}
