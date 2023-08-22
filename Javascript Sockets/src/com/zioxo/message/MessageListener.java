package com.zioxo.message;

public interface MessageListener {

	public void messageRecieved(byte[] message);

	public void exceptionOccured(Throwable t);

	public boolean isClosed();
}
