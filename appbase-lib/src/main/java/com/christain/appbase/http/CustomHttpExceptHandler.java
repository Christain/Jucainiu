package com.christain.appbase.http;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.litesuits.http.data.HttpStatus;
import com.litesuits.http.exception.ClientException;
import com.litesuits.http.exception.HttpClientException;
import com.litesuits.http.exception.HttpNetException;
import com.litesuits.http.exception.HttpServerException;
import com.litesuits.http.exception.NetException;
import com.litesuits.http.exception.ServerException;
import com.litesuits.http.exception.handler.HttpExceptionHandler;

/**
 * 自定义错误回调
 * Created by Administrator on 2015/12/17.
 */
public abstract class CustomHttpExceptHandler extends HttpExceptionHandler {

    private Activity activity;
    private Fragment fragment;
    private boolean isActivity = true;

    public CustomHttpExceptHandler(Activity activity) {
        this.activity = activity;
        this.isActivity = true;
    }

    public CustomHttpExceptHandler(Fragment fragment) {
        this.fragment = fragment;
        this.isActivity = false;
    }

    @Override
    protected void onClientException(HttpClientException e, ClientException type) {
        switch (e.getExceptionType()) {
            case UrlIsNull:
                break;
            case ContextNeeded:
                // some action need app context
                break;
            case PermissionDenied:
                break;
            case SomeOtherException:
                break;
        }
        onFailure(e.toString());
//        HttpUtil.showTips(activity, "ChristainApp", "Client Exception:\n" + e.toString());
        if (isActivity) {
            activity = null;
        } else {
            fragment = null;
        }
    }

    @Override
    protected void onNetException(HttpNetException e, NetException type) {
        switch (e.getExceptionType()) {
            case NetworkNotAvilable:
                break;
            case NetworkUnstable:
                // maybe retried but fail
                break;
            case NetworkDisabled:
                break;
            default:
                break;
        }
        onFailure(e.toString());
//        HttpUtil.showTips(activity, "ChristainApp", "Network Exception:\n" + e.toString());
        if (isActivity) {
            activity = null;
        } else {
            fragment = null;
        }
    }

    @Override
    protected void onServerException(HttpServerException e, ServerException type, HttpStatus status) {
        switch (e.getExceptionType()) {
            case ServerInnerError:
                // status code 5XX error
                break;
            case ServerRejectClient:
                // status code 4XX error
                break;
            case RedirectTooMuch:
                break;
            default:
                break;
        }
        onFailure(e.toString());
//        HttpUtil.showTips(activity, "ChristainApp", "Server Exception:\n" + e.toString());
        if (isActivity) {
            activity = null;
        } else {
            fragment = null;
        }
    }

    public abstract void onFailure(String msg);
}