package com.laobi.hainamall.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.christain.appbase.widget.tablayout.CommonTabLayout;
import com.christain.appbase.widget.tablayout.listener.CustomTabEntity;
import com.laobi.hainamall.R;
import com.laobi.hainamall.baseclass.BaseActivity;
import com.laobi.hainamall.entity.TabEntity;
import com.laobi.hainamall.ui.fragment.SimpleCardFragment;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * 类描述 ：
 * Author: Christain
 * Date  : 16/3/23
 */
public class IndexHome extends BaseActivity {

    @Bind(R.id.tablayout)
    CommonTabLayout tablayout;

    private ArrayList<Fragment> mFragments2 = new ArrayList<>();

    private String[] mTitles = {"首页", "消息", "联系人", "更多"};
    private int[] mIconUnselectIds = {
            R.mipmap.tab_home_unselect, R.mipmap.tab_speech_unselect,
            R.mipmap.tab_contact_unselect, R.mipmap.tab_more_unselect};
    private int[] mIconSelectIds = {
            R.mipmap.tab_home_select, R.mipmap.tab_speech_select,
            R.mipmap.tab_contact_select, R.mipmap.tab_more_select};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index_home);

        for (String title : mTitles) {
            mFragments2.add(SimpleCardFragment.getInstance(title));
        }

        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }

        tablayout.setTabData(mTabEntities, this, R.id.fragment, mFragments2);
        tablayout.setCurrentTab(0);
        tablayout.showDot(1);
        tablayout.showDot(3);
    }
}
