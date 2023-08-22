package com.zioxo.client;

import java.io.*;
import java.net.*;
import java.nio.charset.*;
import java.nio.charset.spi.*;

import com.zioxo.message.*;

public class Client implements MessageListener {

	protected Socket socket = null;

	protected ClientListener listener = null;

	private Thread recieveThread = null;

	protected boolean closed = false;

	public Client(Socket socket) throws IOException {
		this(socket, null);
	}

	public Client(Socket socket, ClientListener listener) throws IOException {
		this.socket = socket;
		this.listener = listener;

		recieveThread = new Thread(new MessageReciever(this, socket.getInputStream()));
		recieveThread.start();
	}

	public static Client create(Socket socket, ClientListener listener) throws IOException {
		return new Client(socket, listener);
	}

	public void send(String message) throws IOException {
		send(message.getBytes("UTF-8"));
	}

	public void send(byte[] message) throws IOException {
		socket.getOutputStream().write(message);
	}

	public void messageRecieved(byte[] message) {
		if (listener != null) {
			listener.messageRecieved(this, new String(message));
		}
	}

	public void exceptionOccured(Throwable t) {
		if (listener == null) {
			return;
		}
		if (t instanceof SocketException) {
			listener.clientDisconnected(this);
		} else {
			t.printStackTrace();
		}
	}

	@Override
	public boolean isClosed() {
		return socket.isClosed();
	}
}
