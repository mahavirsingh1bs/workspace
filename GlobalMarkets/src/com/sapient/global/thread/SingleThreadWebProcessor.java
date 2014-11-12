package com.sapient.global.thread;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SingleThreadWebProcessor {

	public static void main(String...args) throws IOException {
		ServerSocket socket = new ServerSocket(6066);
		while (true) {
			Socket connection = socket.accept();
			handleRequest(connection);
		}
	}

	private static void handleRequest(Socket connection) {
		try {
			InputStream input = connection.getInputStream();
			DataInputStream inputStream = new DataInputStream(input);
			System.out.println("Client Says: " + inputStream.readUTF());
			// inputStream.close();
			OutputStream out = connection.getOutputStream();
			PrintWriter writer = new PrintWriter(out);
			writer.write("Hello World!");
			writer.flush();
			// 	writer.close();
			connection.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
