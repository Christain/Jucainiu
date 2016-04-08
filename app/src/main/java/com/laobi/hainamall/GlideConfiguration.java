package com.laobi.hainamall;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.DiskLruCacheWrapper;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.module.GlideModule;

import java.io.File;

/**
 * 类描述 ：Glide图片配置
 * Author: Christain
 * Date  : 16/3/18
 */
public class GlideConfiguration implements GlideModule {

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        builder.setDiskCache(new DiskCache.Factory() {
            @Override
            public DiskCache build() {
                File cacheLocation = new File(Constants.IMAGE_CACHE_DIR_PATH);
                if (!cacheLocation.exists()) {
                    cacheLocation.mkdirs();
                }
                return DiskLruCacheWrapper.get(cacheLocation, 80 * 1024 * 1024);
            }
        });
        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
        //据调查，一般建议设置为应用可用内存的1/8
        builder.setMemoryCache(new LruResourceCache((int) Runtime.getRuntime().maxMemory() / 8));
    }

    @Override
    public void registerComponents(Context context, Glide glide) {
        // register ModelLoaders here.
    }
}