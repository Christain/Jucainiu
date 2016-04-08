package com.laobi.hainamall.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import com.christain.appbase.superclass.LazyFragment;
import com.christain.appbase.widget.TitleBar;
import com.laobi.hainamall.R;

import butterknife.Bind;
import butterknife.ButterKnife;

@SuppressLint("ValidFragment")
public class SimpleCardFragment extends LazyFragment {

    @Bind(R.id.titlebar)
    TitleBar titlebar;
    @Bind(R.id.textView)
    TextView textView;

    private String mTitle;

    public static SimpleCardFragment getInstance(String title) {
        SimpleCardFragment sf = new SimpleCardFragment();
        sf.mTitle = title;
        return sf;
    }

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_home);
        ButterKnife.bind(this, getContentView());

        titlebar.setTitle(mTitle);
        titlebar.setTitleColor(0xffffffff);

        textView.setText(mTitle);
    }

    @Override
    protected void onDestroyViewLazy() {
        super.onDestroyViewLazy();
        ButterKnife.unbind(this);
    }

}