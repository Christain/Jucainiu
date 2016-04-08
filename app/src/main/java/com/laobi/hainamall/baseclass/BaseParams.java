package com.laobi.hainamall.baseclass;


import com.christain.appbase.http.SuperParams;
import com.laobi.hainamall.Constants;

/**
 * 类描述 ：UrlEncodedForm上传方式参数配置父类
 * Author: Christain
 * Date  : 16/3/21
 */
public class BaseParams extends SuperParams {

    private boolean append;

    public BaseParams(String url) {
        super(url);
        this.append = true;
    }

    /**
     * 是否需要拼接域名
     * @param url
     * @param append
     */
    public BaseParams(String url, boolean append) {
        super(url);
        this.append = false;
    }

    @Override
    public String getUrl() {
        return append ? Constants.APP_API_DOMAINS + super.getUrl() : super.getUrl();
    }
}
