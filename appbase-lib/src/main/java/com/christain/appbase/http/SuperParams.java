package com.christain.appbase.http;


import android.text.TextUtils;

import com.litesuits.http.data.NameValuePair;

import java.util.LinkedList;
import java.util.Map;

/**
 * 类描述 ：UrlEncodedForm上传方式配置Parameter
 * Author: Christain
 * Date  : 16/3/21
 */
public class SuperParams {

    private String url;
    private LinkedList<NameValuePair> list;

    public SuperParams(String url) {
        this.url = (url != null) ? url : "";
        if (list == null) {
            list = new LinkedList<NameValuePair>();
        }
    }

    /**
     * String类型
     * @param key
     * @param value
     */
    public void addParam(String key, String value) {
        list.add(new NameValuePair(key, value));
    }

    /**
     * int类型
     * @param key
     * @param value
     */
    public void addParam(String key, int value) {
        list.add(new NameValuePair(key, String.valueOf(value)));
    }

    /**
     * double类型
     * @param key
     * @param value
     */
    public void addParam(String key, double value) {
        list.add(new NameValuePair(key, String.valueOf(value)));
    }

    /**
     * float类型
     * @param key
     * @param value
     */
    public void addParam(String key, float value) {
        list.add(new NameValuePair(key, String.valueOf(value)));
    }

    /**
     * long类型
     * @param key
     * @param value
     */
    public void addParam(String key, long value) {
        list.add(new NameValuePair(key, String.valueOf(value)));
    }

    /**
     * char类型
     * @param key
     * @param value
     */
    public void addParam(String key, char value) {
        list.add(new NameValuePair(key, String.valueOf(value)));
    }

    /**
     * char[]类型
     * @param key
     * @param value
     */
    public void addParam(String key, char[] value) {
        list.add(new NameValuePair(key, String.valueOf(value)));
    }

    /**
     * Object类型
     * @param key
     * @param value
     */
    public void addParam(String key, Object value) {
        list.add(new NameValuePair(key, String.valueOf(value)));
    }

    /**
     * map类型
     * @param map
     */
    public void addParam(Map<String, String> map) {
        try {
            for (String key : map.keySet()) {
                if (!TextUtils.isEmpty(map.get(key))) {
                    list.add(new NameValuePair(key, map.get(key)));
                } else {
                    list.add(new NameValuePair(key, ""));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public LinkedList<NameValuePair> getParameters() {
        return (list != null) ? list : new LinkedList<NameValuePair>();
    }

    public String getUrl() {
        return url;
    }
}
