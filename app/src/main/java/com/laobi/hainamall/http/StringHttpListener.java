package com.laobi.hainamall.http;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.christain.appbase.common.CheckEmpty;
import com.christain.appbase.http.SuperHttpListener;
import com.laobi.hainamall.BuildConfig;
import com.litesuits.http.response.Response;
import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 类描述 ：网络请求返回监听
 * Author: Christain
 * Date  : 16/3/21
 */
public abstract class StringHttpListener extends SuperHttpListener<String> {

    public StringHttpListener(Activity activity) {
        super(activity);
    }

    public StringHttpListener(Fragment fragment) {
        super(fragment);
    }

    public StringHttpListener(Activity activity, boolean isDisableListener) {
        super(activity, isDisableListener);
    }

    public StringHttpListener(Fragment fragment, boolean isDisableListener) {
        super(fragment, isDisableListener);
    }

    @Override
    public void onServiceSuccessed(String result, Response response) {
        doStatusCheck(result, response);
    }

    @Override
    public void onServiceFailed(int code, String msg) {
        onFailed(code, msg);
    }

    /**
     * 根据和Service端约定的规则，分发处理接口返回的消息
     * @param result
     * @param response
     */
    private void doStatusCheck(String result, Response<String> response) {
        try {
            JSONObject object = new JSONObject(result);
            if (object.has("code") && object.has("message") && object.has("data")) {
                int code = object.getInt("code");
                String message = object.getString("message");
                if (CheckEmpty.isEmpty(message)) {
                    message = "服务器返回消息为空";
                }
                if (code == 1000) {
                    Logger.json(result);
                    String data = object.getString("data");
                    if (!CheckEmpty.isEmpty(data)) {
                        onSuccessed(data, response);
                    } else {
                        onSuccessed("", response);
                    }
                } else {
                    onFailed(code, message);
                }
            } else {
                printError(response, "基本JSON格式不正确");
                onFailed(-1, "基本JSON格式不正确");
            }
        } catch (JSONException e) {
            printError(response, "基本JSON格式不正确");
            onFailed(-1, "基本JSON格式不正确");
            e.printStackTrace();
        }
    }

    private void printError(Response<String> response, String tips) {
        if (BuildConfig.DEBUG) {
            try {
                String requestBody = response.getRequest().getHttpBody().toString();
                Logger.e("请求地址：" + response.getRequest().getUri() + "\n请求参数：" + requestBody.substring(32, requestBody.indexOf("'", 32)) + "\n错误日志：" + tips);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public abstract void onSuccessed(String result, Response response);

    public abstract void onFailed(int code, String msg);
}
