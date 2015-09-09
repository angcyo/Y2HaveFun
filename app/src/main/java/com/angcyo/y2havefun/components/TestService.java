package com.angcyo.y2havefun.components;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.angcyo.y2havefun.util.Logger;

/**
 * Created by angcyo on 15-09-06-006.
 */
public class TestService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Logger.e("onBind" + " <---");
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.e("onCreate" + " <--- " + Thread.currentThread().getId());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Logger.e("onStartCommand" + " <---");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Logger.e("onDestroy" + " <---");
    }
}
