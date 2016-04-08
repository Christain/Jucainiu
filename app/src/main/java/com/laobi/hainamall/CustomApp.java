package com.laobi.hainamall;

import com.christain.appbase.SuperApplication;
import com.lzy.okhttputils.OkHttpUtils;

import java.io.File;

/**
 * 类描述 ：自定义Application
 * Author: Christain
 * Date  : 16/3/18
 */
public class CustomApp extends SuperApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        initOKHttp();
        checkPath();
    }

    /**
     * 初始化OKHttp
     */
    private void initOKHttp() {
        OkHttpUtils.debug(BuildConfig.DEBUG, "MyOkHttp");    //是否打开调试
        try {
            OkHttpUtils.getInstance()//
                    .setConnectTimeout(OkHttpUtils.DEFAULT_MILLISECONDS)//全局的连接超时时间
                    .setReadTimeOut(OkHttpUtils.DEFAULT_MILLISECONDS)//全局的读取超时时间
                    .setWriteTimeOut(OkHttpUtils.DEFAULT_MILLISECONDS);//全局的写入超时时间
                    //.setCertificates(getAssets().open("srca.cer"), getAssets().open("zhy_server.cer"))
                    //.setCertificates(new Buffer().writeUtf8(CER_12306).inputStream());//设置自签名网站的证书

//            RequestHeaders headers = new RequestHeaders();
//            headers.put("aaa", "111");
//            headers.put("bbb", "222");
//            OkHttpUtils.getInstance().addCommonHeader(headers); //全局公共头
//
//            RequestParams params = new RequestParams();
//            params.put("ccc", "333");
//            params.put("ddd", "444");
//            OkHttpUtils.getInstance().addCommonParams(params);  //全局公共参数
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 创建SD卡文件目录
     */
    private void checkPath() {
        File dir = new File(Constants.IMAGE_CACHE_DIR_PATH);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        dir = new File(Constants.HTTP_CACHE_DIR_PATH);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        dir = new File(Constants.TEMP_FILES_DIR_PATH);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

}
