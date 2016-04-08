package com.christain.appbase;

import android.app.Application;
import android.content.Context;

import com.christain.appbase.common.AppExceptionUtil;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

/**
 * 类描述 ：Application
 * Author: Christain
 * Date  : 16/3/21
 */
public class SuperApplication extends Application {
    private static Context mContext;
    private static SuperApplication INSTANCE;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        mContext = getApplicationContext();

        if (!BuildConfig.DEBUG) {
            AppExceptionUtil.start();
        }

        //Log打印
        Logger.init("JCN").setLogLevel(LogLevel.FULL).hideThreadInfo();

    }

    public static synchronized SuperApplication getInstance() {
        return INSTANCE;
    }

    public static synchronized Context getContext() {
        return mContext;
    }
}
