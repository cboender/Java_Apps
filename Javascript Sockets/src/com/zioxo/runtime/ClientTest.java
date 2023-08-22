package com.zioxo.runtime;

import java.io.*;
import java.net.*;

import com.zioxo.client.*;

public class ClientTest {

	public static void main(String[] args) throws UnknownHostException, IOException {
		String host = "127.0.0.1";
		int port = 19000;

		JavaClient client = new JavaClient(host, port);
		client.connect();
		for (int i = 0; i < 100; i++) {
			client.send("Hello World " + i);
		}
//		client.send("Client: Connected");
//		client.send("Hello World");
		client.close();
	}

}
