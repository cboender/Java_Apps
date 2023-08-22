package com.zioxo.server;

import java.io.*;
import java.net.*;
import java.util.*;

import com.zioxo.client.*;

public class Server implements ClientListener {

	private int port = 19000;

	private ServerSocket socket = null;

	private List<Client> clients = new ArrayList<>();

	ClientFactory clientFactory = null;

	public Server() {
		clientFactory = new ClientFactory(this);
	}

	public void start() throws IOException {
		socket = new ServerSocket(port);

		while (true) {
			try {
				createClient();
			} catch (IOException exp) {
				exp.printStackTrace();
			}
		}
	}

	private void createClient() throws IOException {
		Socket clientSocket = socket.accept();
		Server server = this;
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					clientFactory.initializeClient(clientSocket, server);
				} catch (IOException exp) {
					// TODO Auto-generated catch block
					exp.printStackTrace();
				}
			}
		}).start();

	}

	public void addClient(Client client) {
		this.clients.add(client);
	}

	@Override
	public void clientDisconnected(Client client) {
		System.out.println("Client: Disconnected");
		clients.remove(client);
	}

	public void sendMessage(String message) throws IOException {
		for (Client client : clients) {
			client.send(message);
		}
	}

	@Override
	public void messageRecieved(Client client, String message) {
		System.out.println("Message from client: " + message);
	}
}
