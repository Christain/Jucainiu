package com.christain.appbase.widget.mvchelper.mvc;

public interface ResponseSender<DATA> {

	public void sendError(Exception exception);

	public void sendData(DATA data);

}