package com.christain.appbase.widget.mvchelper.mvc;

public interface IAsyncDataSource<DATA> {

	public RequestHandle refresh(ResponseSender<DATA> sender) throws Exception;

	public RequestHandle loadMore(ResponseSender<DATA> sender) throws Exception;

	public boolean hasMore();

}
