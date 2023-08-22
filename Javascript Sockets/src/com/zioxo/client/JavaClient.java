package com.zioxo.client;

import java.io.*;
import java.net.*;

public class JavaClient implements ClientListener {

	private String host;
	private int port;

	private Client client;

	public JavaClient(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public void connect() throws UnknownHostException, IOException {
		if (client != null) {
			System.out.println("Client is already initialized");
			return;
		}
		Socket server = new Socket(host, port);
		client = new Client(server, this);
		client.send("Fake header from client");
		try {
			Thread.sleep(100);
		} catch (InterruptedException exp) {
			// TODO Auto-generated catch block
			exp.printStackTrace();
		}
	}

	public void send(String message) throws IOException {
		if (client == null) {
			throw new IOException("Client hasn't been connected");
		}
		client.send(message);
		try {
			Thread.sleep(0);
		} catch (InterruptedException exp) {
			// TODO Auto-generated catch block
			exp.printStackTrace();
		}
	}

	public void close() {
		if (client != null) {
			try {
				client.socket.close();
			} catch (IOException exp) {
				exp.printStackTrace();
			}
		}
	}

	@Override
	public void clientDisconnected(Client client) {
		System.out.println("Connection to server has closed");
	}

	@Override
	public void messageRecieved(Client client, String message) {
		System.out.println(message);
	}
}
