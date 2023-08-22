package com.zioxo.message;

import java.io.*;
import java.net.*;
import java.util.*;

public class MessageReciever implements Runnable {

	protected static final int BUFFER_SIZE = 1024;

	protected MessageListener listener = null;

	protected InputStream input = null;

	protected boolean oneRun = false;

	public MessageReciever(MessageListener listener, InputStream input) {
		this.listener = listener;
		this.input = input;
	}

	public void setOneRun(boolean oneRun) {
		this.oneRun = oneRun;
	}

	@Override
	public void run() {
		BufferedInputStream bis = new BufferedInputStream(input);
		try {
			byte[] data = new byte[BUFFER_SIZE];
			int read = 0;

			while ((read = bis.read(data)) > 0) {
				// TODO Handle messages larger than the buffer size
				listener.messageRecieved(Arrays.copyOf(data, read));
				if (oneRun) {
					break;
				}
			}
		} catch (IOException e) {
			listener.exceptionOccured(e);
		}
	}
}
