package com.angcyo.y2havefun.mode.event;

/**
 * Created by angcyo on 15-09-07-007.
 */
public class LoadedEvent {
    public static int CODE_OK = 1;
    public static int CODE_FAIL = 0;

    public int code = 0;// 1:完成, 0未完成

    public LoadedEvent(int code) {
        this.code = code;
    }
}
