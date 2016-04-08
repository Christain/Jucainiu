package com.laobi.hainamall.baseclass;

import com.christain.appbase.http.SuperMultipartParams;
import com.laobi.hainamall.Constants;

/**
 * 类描述 ：表单上传参数配置父类
 * Author: Christain
 * Date  : 16/3/21
 */
public class MultipartParams extends SuperMultipartParams {

    private boolean append;

    public MultipartParams(String url) {
        super(url);
        this.append = true;
    }

    /**
     * 是否需要拼接域名
     * @param url
     * @param append
     */
    public MultipartParams(String url, boolean append) {
        super(url);
        this.append = false;
    }

    @Override
    public String getUrl() {
        return append ? Constants.APP_API_DOMAINS + super.getUrl() : super.getUrl();
    }
}
