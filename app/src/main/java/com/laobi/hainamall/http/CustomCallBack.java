package com.laobi.hainamall.http;

import android.support.annotation.Nullable;

import com.alibaba.fastjson.JSON;
import com.lzy.okhttputils.callback.AbsCallback;
import com.lzy.okhttputils.request.BaseRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Description :
 * Author : Christain
 * Date   : 2016/4/6
 */
public abstract class CustomCallBack<T> extends AbsCallback<T> {

    @Override
    public T parseNetworkResponse(Response response) throws Exception {
        if (response.isSuccessful()) {
            String responseStr = response.body().string();
            JSONObject object = new JSONObject(responseStr);
            int code = object.getInt("errno");
            if (code == 0) {
                Type type = this.getClass().getGenericSuperclass();
                if (type instanceof ParameterizedType) {
                    //如果用户写了泛型，就会进入这里，否者不会执行
                    ParameterizedType parameterizedType = (ParameterizedType) type;
                    Type beanType = parameterizedType.getActualTypeArguments()[0];
                    if (beanType == String.class) {
                        //如果是String类型，直接返回字符串
                        return (T) responseStr;
                    } else {
                        //如果是 Bean List Map ，则解析完后返回
                        return JSON.parseObject(responseStr, beanType);
                    }
                } else {
                    return (T) response;
                }
            }
        }
        return null;
    }

    @Override
    public void onAfter(@Nullable T t, Call call, Response response, @Nullable Exception e) {
        System.out.println("onAfter");
    }

    @Override
    public void upProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
        System.out.println("upProgress -- " + totalSize + "  " + currentSize + "  " + progress + "  " + networkSpeed);
    }

    @Override
    public void downloadProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
        System.out.println("downloadProgress -- " + totalSize + "  " + currentSize + "  " + progress + "  " + networkSpeed);
    }

    @Override
    public void onError(Call call, @Nullable Response response, @Nullable Exception e) {
        System.out.println("onError");
//        super.onError(call, response, e);
        if (response.isSuccessful()) {
            try {
                String responseStr = response.body().string();
                JSONObject object = new JSONObject(responseStr);
                int code = object.getInt("errno");
                if (code == 0) {
                    onFailed(code, "数据解析错误", response);
                } else {
                    onFailed(code, "数据解析错误", response);
                }
            } catch (IOException e1) {
                e1.printStackTrace();
                onFailed(-1, "响应数据错误", response);
            } catch (JSONException e1) {
                e1.printStackTrace();
                onFailed(-1, "基本数据解析错误", response);
            }
        } else {
            if (e != null) {
                onFailed(-1, e.toString(), response);
            } else {
                onFailed(-1, "网路连接失败" + response.code(), response);
            }
        }
    }

    @Override
    public void onResponse(T t) {
        onSuccessed(t);
    }

    @Override
    public void onBefore(BaseRequest request) {
        System.out.println("onBefore");
    }

    public abstract void onSuccessed(T result);

    public abstract void onFailed(int code, String msg, Response response);
}
