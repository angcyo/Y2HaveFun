package com.angcyo.y2havefun;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

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

    //初始化
    private void init() {
        handler = new StaticHandler(this);
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
