package com.angcyo.y2havefun.util;

/**
 * Created by angcyo on 15-07-25-025.
 */

import android.util.Log;

import java.io.PrintWriter;
import java.io.StringWriter;

public class Logger {
    public static final String TAG = "Y2HaveFun";
    private static boolean isDebugOn = true;
    private static boolean isInfoOn = true;
    private static boolean isWarnOn = true;
    private static boolean isVerboseOn = true;
    private static boolean isRelease = false;
    private static boolean isError = true;

    public static void d(String tag, String message) {
        if (isRelease) {
            Log.d(TAG, tag + ":" + message);
            return;
        }
        if (isDebugOn) {
            Log.d(tag, message);
        }
    }

    public static void v(String tag, String message) {
        if (isRelease) {
            Log.v(TAG, tag + ":" + message);
            return;
        }
        if (isVerboseOn) {
            Log.d(tag, message);
        }
    }

    public static void w(String tag, String message) {
        if (isRelease) {
            Log.w(TAG, tag + ":" + message);
            return;
        }
        if (isWarnOn) {
            Log.w(tag, message);
        }
    }

    public static void i(String tag, String message) {
        if (isRelease) {
            Log.i(TAG, tag + ":" + message);
            return;
        }
        if (isInfoOn) {
            Log.i(tag, message);
        }
    }

    public static void e(String tag, String message) {
        if (isRelease) {
            tag = TAG;
        }
        Log.e(tag, message);
    }

    public static void d(String message) {
        if (isDebugOn) {
            Log.d(TAG, message);
        }
    }

    public static void w(String message) {
        if (isWarnOn) {
            Log.w(TAG, message);
        }
    }

    public static void i(String message) {
        if (isInfoOn) {
            Log.i(TAG, message);
        }
    }

    public static void e(String message) {
        if (isError)
            Log.e(TAG, message);
    }

    public static void customPrintStackTrace(Exception ex) {
        StringWriter errors = new StringWriter();
        ex.printStackTrace(new PrintWriter(errors));
        Log.e(TAG, errors.toString());
    }
}

