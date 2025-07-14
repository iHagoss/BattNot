package com.hagos.battnot.application;

import android.app.Application;
import android.content.Context;
import android.os.Build;

import me.weishu.reflection.Reflection;

public class BattNot extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        if (Build.VERSION.SDK_INT >= 28) {
            //The nuclear option
            Reflection.unseal(base);
        }
    }
}
