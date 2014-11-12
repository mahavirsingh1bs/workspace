package com.sapient.global.thread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ServerClient {

	public static void main(String...args) throws UnknownHostException, IOException {
		Socket client = new Socket("localhost", 6066);
		OutputStream clientOutStream = client.getOutputStream();
		DataOutputStream out = new DataOutputStream(clientOutStream);
		out.writeUTF("Hello from " + client.getLocalSocketAddress());
		DataInputStream in = new DataInputStream(client.getInputStream());
		System.out.println(in.readUTF());
		client.close();
	}
}
