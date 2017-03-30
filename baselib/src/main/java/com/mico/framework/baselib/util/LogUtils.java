package com.mico.framework.baselib.util;

import android.text.TextUtils;
import android.util.Log;

/**
 * Created by LiuNana on 2017/3/15.
 * 日志打印工具类
 */
public class LogUtils {
    private static final int VERBOSE = 1;
    private static final int DEBUG = 2;
    private static final int INFO = 3;
    private static final int WARN = 4;
    private static final int ERROR = 5;
    private static int LEVEL = VERBOSE;

    private LogUtils() {
    }

    public static void setLogLevel(int level){
        LEVEL = level;
    }

    /**
     * 打印verbose日志
     *
     * @param tag 标志
     * @param msg 信息
     */
    public static void v(String tag, String msg) {
        if (LEVEL <= VERBOSE) {
            if (TextUtils.isEmpty(tag)) {
                v(msg);
                return;
            }
            Log.v(tag, msg);
        }
    }

    /**
     * 打印verbose日志
     *
     * @param msg 信息
     */
    public static void v(String msg) {
        if (LEVEL <= VERBOSE) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            String tag = getDefaultTag(stackTraceElement);
            Log.v(tag, msg);
        }
    }

    /**
     * 打印debug日志
     *
     * @param tag 标志
     * @param msg 信息
     */
    public static void d(String tag, String msg) {
        if (LEVEL <= DEBUG) {
            if (TextUtils.isEmpty(tag)) {
                d(msg);
                return;
            }
            Log.d(tag, msg);
        }
    }

    /**
     * 打印debug日志
     *
     * @param msg 信息
     */
    public static void d(String msg) {
        if (LEVEL <= DEBUG) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            String tag = getDefaultTag(stackTraceElement);
            Log.d(tag, msg);
        }
    }

    /**
     * 打印info日志
     *
     * @param tag 标志
     * @param msg 信息
     */
    public static void i(String tag, String msg) {
        if (LEVEL <= INFO) {
            if (TextUtils.isEmpty(tag)) {
                i(msg);
                return;
            }
            Log.i(tag, msg);
        }
    }

    /**
     * 打印info日志
     *
     * @param msg 信息
     */
    public static void i(String msg) {
        if (LEVEL <= INFO) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            String tag = getDefaultTag(stackTraceElement);
            Log.i(tag, msg);
        }
    }

    /**
     * 打印warn日志
     *
     * @param tag 标志
     * @param msg 信息
     */
    public static void w(String tag, String msg) {
        if (LEVEL <= WARN) {
            if (TextUtils.isEmpty(tag)) {
                w(msg);
                return;
            }
            Log.w(tag, msg);
        }
    }

    /**
     * 打印warn日志
     *
     * @param msg 信息
     */
    public static void w(String msg) {
        if (LEVEL <= WARN) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            String tag = getDefaultTag(stackTraceElement);
            Log.w(tag, msg);
        }
    }

    /**
     * 打印error日志
     *
     * @param tag 标志
     * @param msg 信息
     * @param t   异常
     */
    public static void e(String tag, String msg, Throwable t) {
        if (LEVEL <= ERROR) {
            if (TextUtils.isEmpty(tag)) {
                e(msg, t);
                return;
            }
            Log.e(tag, msg, t);
        }
    }

    /**
     * 打印error日志
     *
     * @param t 异常
     */
    public static void e(Throwable t) {
        if (LEVEL <= ERROR) {
            e("", t);
        }
    }

    /**
     * 打印error日志
     *
     * @param msg 信息
     * @param t   异常
     */
    public static void e(String msg, Throwable t) {
        if (LEVEL <= ERROR) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            String tag = getDefaultTag(stackTraceElement);
            Log.e(tag, msg, t);
        }
    }

    /**
     * 打印error日志
     *
     * @param tag 标志
     * @param msg 信息
     */
    public static void e(String tag, String msg) {
        if (LEVEL <= ERROR) {
            if (TextUtils.isEmpty(tag)) {
                e(msg);
                return;
            }
            Log.e(tag, msg);
        }
    }

    /**
     * 打印error日志
     *
     * @param msg 信息
     */
    public static void e(String msg) {
        if (LEVEL <= ERROR) {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
            String tag = getDefaultTag(stackTraceElement);
            Log.e(tag, msg);
        }
    }

    /**
     * 获取默认的TAG名称.
     * 比如在MainActivity.java中调用了日志输出.
     * 则TAG为MainActivity
     */
    private static String getDefaultTag(StackTraceElement stackTraceElement) {
        String fileName = stackTraceElement.getFileName();
        String stringArray[] = fileName.split("\\.");
        return stringArray[0];
    }
}
