package com.christain.appbase.http;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.christain.appbase.BuildConfig;
import com.litesuits.http.exception.HttpException;
import com.litesuits.http.listener.HttpListener;
import com.litesuits.http.response.Response;
import com.orhanobut.logger.Logger;


/**
 * 类描述 ：自定义String回调
 * Author: Christain
 * Date  : 16/3/21
 */
public abstract class SuperHttpListener<T> extends HttpListener<T> {

    private Activity activity;
    private Fragment fragment;
    private boolean isActivity = true;
    private boolean isDisableListener = true;//判断Activity或者Fragment销毁时，接口回调是否失效

    public SuperHttpListener(Activity activity) {
        this.activity = activity;
        this.isActivity = true;
        this.isDisableListener = true;
    }

    public SuperHttpListener(Fragment fragment) {
        this.fragment = fragment;
        this.isActivity = false;
        this.isDisableListener = true;
    }

    public SuperHttpListener(Activity activity, boolean isDisableListener) {
        this.activity = activity;
        this.isActivity = true;
        this.isDisableListener = isDisableListener;
    }

    public SuperHttpListener(Fragment fragment, boolean isDisableListener) {
        this.fragment = fragment;
        this.isActivity = false;
        this.isDisableListener = isDisableListener;
    }

    @Override
    public boolean disableListener() {
        if (isDisableListener) {
            if (isActivity) {
                return activity == null || activity.isFinishing();
            } else {
                return fragment == null || fragment.getActivity() == null || fragment.getActivity().isFinishing();
            }
        } else {
            return isDisableListener;
        }
    }

    @Override
    public void onSuccess(T reuslt, Response<T> response) {
        onServiceSuccessed((String) reuslt, response);
    }

    @Override
    public void onFailure(HttpException e, Response response) {
        printErrorLog(response, e.toString());
        if (isActivity) {
            if (activity != null) {
                new CustomHttpExceptHandler(activity) {
                    @Override
                    public void onFailure(String msg) {
                        onServiceFailed(-2, msg);
                    }
                }.handleException(e);
            }
        } else {
            if (fragment != null) {
                new CustomHttpExceptHandler(fragment) {
                    @Override
                    public void onFailure(String msg) {
                        onServiceFailed(-2, msg);
                    }
                }.handleException(e);
            }
        }
    }

    private void printErrorLog(Response<T> response, String tips) {
        if (BuildConfig.DEBUG) {
            try {
                String requestBody = response.getRequest().getHttpBody().toString();
                Logger.e("请求地址：" + response.getRequest().getUri() + "\n请求参数：" + requestBody + "\n错误日志：" + tips);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public abstract void onServiceSuccessed(String result, Response response);

    public abstract void onServiceFailed(int code, String msg);
}
