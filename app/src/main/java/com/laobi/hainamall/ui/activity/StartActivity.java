package com.laobi.hainamall.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.christain.appbase.common.Toastor;
import com.christain.appbase.http.PostRequest;
import com.christain.appbase.widget.TitleBar;
import com.laobi.hainamall.API;
import com.laobi.hainamall.R;
import com.laobi.hainamall.baseclass.BaseActivity;
import com.laobi.hainamall.baseclass.BaseParams;
import com.laobi.hainamall.http.CustomCallBack;
import com.laobi.hainamall.http.LiteHttpUtils;
import com.laobi.hainamall.http.StringHttpListener;
import com.litesuits.http.response.Response;
import com.lzy.okhttputils.OkHttpUtils;

import butterknife.Bind;

/**
 * 类描述 ：启动页
 * Author: Christain
 * Date  : 16/3/18
 */
public class StartActivity extends BaseActivity {

    @Bind(R.id.titlebar)
    TitleBar titlebar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titlebar.setTitle("启动页");
        titlebar.setTitleColor(0xffffffff);

        BaseParams params = new BaseParams(API.API_REGISTER);
        LiteHttpUtils.getInstance().executeAsync(new PostRequest(params).setHttpListener(
                new StringHttpListener(this) {
                    @Override
                    public void onSuccessed(String result, Response response) {

                    }

                    @Override
                    public void onFailed(int code, String msg) {
                        new Toastor(StartActivity.this).showToast("失败");
                    }
                }));

        OkHttpUtils.put(API.API_REGISTER)
                .tag(this)
                .execute(new CustomCallBack<String>() {
                    @Override
                    public void onSuccessed(String result) {

                    }

                    @Override
                    public void onFailed(int code, String msg, okhttp3.Response response) {

                    }
                });

        titlebar.setOnTitleClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String json = "{\n" +
//                        "  \"vercode\": 4,\n" +
//                        "  \"vername\": \"v1.1\",\n" +
//                        "  \"download\": \"http://www.apk.anzhi.com/data3/apk/201506/09/3a978f27369b4a8bf6de1270da9871ec_86281300.apk\",\n" +
//                        "  \"log\": \"upgrade content\"\n" +
//                        "}";
//                AppUpdateManager.check(StartActivity.this, json, R.mipmap.ic_launcher, "/jucainiu", "聚财牛");
                Intent intent = new Intent(StartActivity.this, IndexHome.class);
                startActivity(intent);
            }
        });
    }
}
