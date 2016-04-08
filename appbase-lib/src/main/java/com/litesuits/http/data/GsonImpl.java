package com.litesuits.http.data;

import com.alibaba.fastjson.JSON;

import java.lang.reflect.Type;

/**
 * google gson
 *
 * @author MaTianyu
 *         2014-2-26下午11:13:39
 */
public class GsonImpl extends Json {
    private static final String TAG = GsonImpl.class.getSimpleName();

    @Override
    public String toJson(Object src) {
        return JSON.toJSONString(src);
    }

    @Override
    public <T> T toObject(String json, Class<T> claxx) {
        return JSON.parseObject(json, claxx);
    }

    @Override
    public <T> T toObject(String s, Type type) {
        return JSON.parseObject(s, type);
    }

    @Override
    public <T> T toObject(byte[] bytes, Class<T> claxx) {
        return JSON.parseObject(bytes, claxx);
    }
}
