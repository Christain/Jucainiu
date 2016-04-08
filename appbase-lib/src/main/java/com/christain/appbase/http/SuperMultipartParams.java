package com.christain.appbase.http;

import com.litesuits.http.request.content.multi.FilePart;
import com.litesuits.http.request.content.multi.MultipartBody;
import com.litesuits.http.request.content.multi.StringPart;

import java.io.File;

/**
 * 类描述 ：表单上传方式配置Paramters
 * Author: Christain
 * Date  : 16/3/21
 */
public class SuperMultipartParams {

    private String url;
    private MultipartBody body;

    public SuperMultipartParams(String url) {
        this.url = (url != null) ? url : "";
        if (body == null) {
            body = new MultipartBody();
        }
    }

    /**
     * String类型
     * @param key
     * @param value
     */
    public void addParam(String key, String value) {
        body.addPart(new StringPart(key, value));
    }

    /**
     * int类型
     * @param key
     * @param value
     */
    public void addParam(String key, int value) {
        body.addPart(new StringPart(key, String.valueOf(value)));
    }

    /**
     * double类型
     * @param key
     * @param value
     */
    public void addParam(String key, double value) {
        body.addPart(new StringPart(key, String.valueOf(value)));
    }

    /**
     * float类型
     * @param key
     * @param value
     */
    public void addParam(String key, float value) {
        body.addPart(new StringPart(key, String.valueOf(value)));
    }

    /**
     * long类型
     * @param key
     * @param value
     */
    public void addParam(String key, long value) {
        body.addPart(new StringPart(key, String.valueOf(value)));
    }

    /**
     * char类型
     * @param key
     * @param value
     */
    public void addParam(String key, char value) {
        body.addPart(new StringPart(key, String.valueOf(value)));
    }

    /**
     * char[]类型
     * @param key
     * @param value
     */
    public void addParam(String key, char[] value) {
        body.addPart(new StringPart(key, String.valueOf(value)));
    }

    /**
     * Object类型
     * @param key
     * @param value
     */
    public void addParam(String key, Object value) {
        body.addPart(new StringPart(key, String.valueOf(value)));
    }

    /**
     * File类型
     * @param key
     * @param value
     */
    public void addParam(String key, File value) {
        body.addPart(new FilePart(key, value));
    }

    /**
     * File类型
     * @param key
     * @param value
     * @param type 例如："image/jpeg"
     */
    public void addParam(String key, File value, String type) {
        body.addPart(new FilePart(key, value, type));
    }

    public MultipartBody getParamters() {
        return body;
    }

    public String getUrl() {
        return url;
    }
}
