package com.wu.corelib.utils;

import android.util.Log;

import com.wu.corelib.BuildConfig;

public class XLog {

    private static final String TAG = "wu";

//    private static FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
//            .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
//            .methodCount(0)         // (Optional) How many method line to show. Default 2
//            .methodOffset(7)        // (Optional) Hides internal method calls up to offset. Default 5
//            .logStrategy(customLog) // (Optional) Changes the log strategy to print out. Default LogCat
//            .tag("My custom tag")   // (Optional) Global tag for every log. Default PRETTY_LOGGER
//            .build();


    public static void i(String tag, Object o) {
        if (BuildConfig.DEBUG) {
            Log.i(TAG, "【" + tag + "】" + o.toString());
        }
    }

    public static void i(Object o) {
        if (BuildConfig.DEBUG) {
            Log.i(TAG, "【" + o.toString() + "】");
        }
    }

    public static void d(String tag, Object o) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "【" + tag + "】" + o.toString());
        }
    }

    public static void d(Object o) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, "【" + o.toString() + "】");
        }
    }

    public static void e(String tag, Object o) {
        if (BuildConfig.DEBUG) {
            Log.e(TAG, "【" + tag + "】" + o.toString());
        }
    }

    public static void e(Object o) {
        if (BuildConfig.DEBUG) {
            Log.e(TAG, "【" + o.toString() + "】");
        }
    }

    public static void w(String tag, Object o) {
        if (BuildConfig.DEBUG) {
            Log.w(TAG, "【" + tag + "】" + o.toString());
        }
    }
}
