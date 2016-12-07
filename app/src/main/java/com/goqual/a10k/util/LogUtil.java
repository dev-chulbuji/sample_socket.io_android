package com.goqual.a10k.util;

import android.util.Log;

import com.goqual.a10k.Application10k;

/**
 * Created by ladmusician on 2016. 12. 7..
 */

public class LogUtil {
    /** Log Level Error **/
    public static final void e(String TAG, String message) {
        if (Application10k.DEBUG) Log.e(TAG, buildLogMsg(message));
    }
    /** Log Level Warning **/
    public static final void w(String TAG, String message) {
        if (Application10k.DEBUG)Log.w(TAG, buildLogMsg(message));
    }
    /** Log Level Information **/
    public static final void i(String TAG, String message) {
        if (Application10k.DEBUG)Log.i(TAG, buildLogMsg(message));
    }
    /** Log Level Debug **/
    public static final void d(String TAG, String message) {
        if (Application10k.DEBUG)Log.d(TAG, buildLogMsg(message));
    }
    /** Log Level Verbose **/
    public static final void v(String TAG, String message) {
        if (Application10k.DEBUG)Log.v(TAG, buildLogMsg(message));
    }


    public static String buildLogMsg(String message) {
        StackTraceElement ste = Thread.currentThread().getStackTrace()[4];
        StringBuilder sb = new StringBuilder();

        sb.append("[");
        sb.append(ste.getFileName().replace(".java", ""));
        sb.append("::");
        sb.append(ste.getMethodName());
        sb.append("]");
        sb.append(message);

        return sb.toString();
    }
}
