package com.angcyo.y2havefun;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.ColorRes;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.bumptech.glide.Glide;
import com.readystatesoftware.systembartint.SystemBarTintManager;

public abstract class BaseActivity extends AppCompatActivity {

    public static Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        init();
        initBefore();
        super.onCreate(savedInstanceState);
        initView();
        initAfter();
        initEvent();
    }

    @TargetApi(19)
    protected void initWindow(@ColorRes int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//状态栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);//导航栏
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setTintResource(color);//设置状态栏颜色
            tintManager.setStatusBarTintEnabled(true);
//            tintManager.setNavigationBarTintResource(R.color.dark_green);//设置导航栏颜色
//            tintManager.setNavigationBarTintEnabled(false);
        }
    }

    //初始化
    protected void init() {
        handler = new StaticHandler(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Glide.get(this).clearMemory();
    }

    /**
     * Init before _.
     */
    protected void initBefore() {

    }

    protected void initEvent() {

    }

    /**
     * Init view.
     */
    protected abstract void initView();

    /**
     * Init after.
     */
    protected abstract void initAfter();

    protected void launchActivity(Class c) {
        Intent intent = new Intent(this, c);
        startActivity(intent);
    }

    protected void handMessage(Message msg, int what, Object obj) {

    }

    public void sendMessage(Message msg) {
        handler.sendMessage(msg);
    }

    public void sendMessage(int what, Object obj) {
        Message msg = handler.obtainMessage();
        msg.what = what;
        msg.obj = obj;
        handler.sendMessage(msg);
    }


    static class StaticHandler extends Handler {
        BaseActivity context;

        public StaticHandler(BaseActivity context) {
            this.context = context;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (context != null && msg != null) {
                context.handMessage(msg, msg.what, msg.obj);
            }
        }
    }
}
