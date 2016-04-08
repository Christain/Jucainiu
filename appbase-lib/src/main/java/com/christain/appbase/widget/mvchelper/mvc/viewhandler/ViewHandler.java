package com.christain.appbase.widget.mvchelper.mvc.viewhandler;

import android.view.View;
import android.view.View.OnClickListener;

import com.christain.appbase.widget.mvchelper.mvc.IDataAdapter;
import com.christain.appbase.widget.mvchelper.mvc.ILoadViewFactory.ILoadMoreView;
import com.christain.appbase.widget.mvchelper.mvc.MVCHelper.OnScrollBottomListener;

public interface ViewHandler {

	/**
	 * 
	 * @param contentView
	 * @param adapter
	 * @param loadMoreView
	 * @param onClickLoadMoreListener
	 * @return 是否有 init ILoadMoreView
	 */
	public boolean handleSetAdapter(View contentView, IDataAdapter<?> adapter, ILoadMoreView loadMoreView, OnClickListener onClickLoadMoreListener);

	public void setOnScrollBottomListener(View contentView, OnScrollBottomListener onScrollBottomListener);

}
