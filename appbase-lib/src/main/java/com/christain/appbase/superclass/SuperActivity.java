package com.christain.appbase.superclass;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.christain.appbase.common.ActivityStackUtil;

import butterknife.ButterKnife;

/**
 * 类描述 ：Activity父类
 * Author: Christain
 * Date  : 16/3/18
 */
public class SuperActivity extends AppCompatActivity implements ActivityStackUtil.ActivityInter {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStackUtil.getInstance().addActivity(this);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        ButterKnife.bind(this);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    @Override
    public void finish() {
        ActivityStackUtil.getInstance().finishActivity(this);
    }

    @Override
    public void finishActivity() {
        super.finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

}
