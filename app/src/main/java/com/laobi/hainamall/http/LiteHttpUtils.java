package com.laobi.hainamall.http;

import com.christain.appbase.SuperApplication;
import com.laobi.hainamall.BuildConfig;
import com.laobi.hainamall.Constants;
import com.litesuits.http.HttpConfig;
import com.litesuits.http.LiteHttp;
import com.litesuits.http.concurrent.OverloadPolicy;
import com.litesuits.http.concurrent.SchedulePolicy;
import com.litesuits.http.request.param.CacheMode;
import com.litesuits.http.request.param.HttpMethods;

/**
 * 类描述 ：LiteHttp请求单列类
 * Author: Christain
 * Date  : 16/3/21
 */
public class LiteHttpUtils {

    private static LiteHttp liteHttp = LiteHttp.newApacheHttpClient(null);

    static {
        //获取CPU数量
        int cpuNums = Runtime.getRuntime().availableProcessors();
        if (cpuNums <= 0) {
            cpuNums = 2;
        }
        HttpConfig config = new HttpConfig(SuperApplication.getContext());
        if (BuildConfig.DEBUG) {
            config.setDebugged(true);
        } else {
            config.setDebugged(false);
        }
        config.setDetectNetwork(true);
        config.setDoStatistics(true);
        config.setDefaultCacheDir(Constants.HTTP_CACHE_DIR_PATH);
        config.setDefaultCacheMode(CacheMode.NetOnly);
        config.setDefaultHttpMethod(HttpMethods.Post);
        config.setDefaultMaxRedirectTimes(5);
        config.setDefaultMaxRetryTimes(1);
        config.setTimeOut(15000, 15000);
        config.setMaxMemCacheBytesSize(1024 * 300);
        config.setConcurrentSize(cpuNums);
        config.setWaitingQueueSize(100);
        config.setOverloadPolicy(OverloadPolicy.DiscardOldTaskInQueue);
        config.setSchedulePolicy(SchedulePolicy.FirstInFistRun);
        liteHttp.initConfig(config);
    }

    private LiteHttpUtils() {

    }

    public static LiteHttp getInstance() {
        return liteHttp;
    }

}
