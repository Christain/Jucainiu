package com.laobi.hainamall;


import com.christain.appbase.common.PhoneUtil;

/**
 * 类描述 ：常量类
 * Author: Christain
 * Date  : 16/3/18
 */
public class Constants {

    /**
     * 正式环境常量配置
     */
    public static final String APP_API_DOMAINS = "http://www.hao123.com";


    /**
     * 测试环境常量配置
     */
    public static final String APP_API_DOMAINS_TEST = "";


    /**
     * SD卡文件创建目录
     */
    public static final String IMAGE_CACHE_DIR_PATH = PhoneUtil.getSdCardRootPath() + "/Jucainiu/cache/";// 图片缓存地址
    public static final String HTTP_CACHE_DIR_PATH = PhoneUtil.getSdCardRootPath() + "/Jucainiu/http-cache/";// 网络缓存地址
    public static final String TEMP_FILES_DIR_PATH = PhoneUtil.getSdCardRootPath() + "/Jucainiu/temp/";//临时文件

}
