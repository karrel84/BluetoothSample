package com.karrel.bluetoothsample.etc;

import android.app.Application;
import android.content.Context;

import com.karrel.mylibrary.RLog;

import io.realm.Realm;

/**
 * Created by Rell on 2017. 8. 30..
 */

public class MyApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        RLog.setEnable(false);
        Realm.init(this);
    }
}
