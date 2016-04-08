package com.christain.appbase.widget.mvchelper.task;

public interface ResponseSender<SUCCESS, FAIL> {

	public void sendError(Exception exception);

	public void sendData(SUCCESS data);

	public void sendFail(FAIL data);

}