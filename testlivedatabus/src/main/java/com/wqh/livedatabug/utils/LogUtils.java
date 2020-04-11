package com.wqh.livedatabug.utils;

import android.util.Log;

import com.wqh.livedatabug.R;

/**
 * 作者: Wang on 2019/11/23 21:32
 * 寄语：加油！相信自己可以！！！
 */


public class LogUtils {
    private static String TAG = "WQH "+R.string.app_name;

    public static void w(String msg) {
        Log.w(TAG,msg);
    }

    public static void e(String msg) {
        Log.e(TAG,msg);
    }
}
