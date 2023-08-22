package com.zioxo.server;

import com.zioxo.message.*;

public class ClientFactoryListener implements MessageListener {
	private byte[] message = null;

	private Throwable t = null;

	public byte[] getMessage() {
		return message;
	}

	@Override
	public void messageRecieved(byte[] message) {
		this.message = message;
	}

	@Override
	public void exceptionOccured(Throwable t) {
		this.t = t;
	}

	public Throwable getException() {
		return t;
	}

	/**
	 * ALways returns false
	 */
	@Override
	public boolean isClosed() {
		return false;
	}
}