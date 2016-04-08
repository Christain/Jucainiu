package com.christain.appbase.http;

import com.litesuits.http.request.AbstractRequest;
import com.christain.appbase.widget.mvchelper.mvc.RequestHandle;

/**
 * 针对MVCHelp请求定制的RequestHandle
 */
public class AsyncRequestHandle implements RequestHandle {
	private AbstractRequest request;

	public AsyncRequestHandle(AbstractRequest request) {
		super();
		this.request = request;
	}

	@Override
	public void cancle() {
		request.cancel();
	}

	@Override
	public boolean isRunning() {
		return false;
	}

}
