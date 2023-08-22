package com.zioxo.client;

public interface ClientListener {
	public void clientDisconnected(Client client);

	public void messageRecieved(Client client, String message);
}
