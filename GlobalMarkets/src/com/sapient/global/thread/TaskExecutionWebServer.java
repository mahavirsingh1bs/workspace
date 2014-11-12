package com.sapient.global.thread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

public class TaskExecutionWebServer {
	private static final int NTHREADS = 100;
	private static final ExecutorService exec = 
			Executors.newFixedThreadPool(NTHREADS);
	
	public static void main(String...args) throws IOException {
		ServerSocket socket = new ServerSocket(6066);
		while (true) {
			final Socket connection = socket.accept();
			Runnable task = new Runnable() {
				@Override
				public void run() {
					handleRequest(connection);
				}
			};
			try {
				exec.execute(task);
			} catch (RejectedExecutionException ex) {
				System.out.println(ex);
				if (!exec.isShutdown()) {
					System.out.println("task submission rejected");
				}
			}
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			exec.shutdown();
			/*List<Runnable> tasks = exec.shutdownNow();
			for (Runnable abortedTask : tasks) {
				System.out.println("Aborted Task: " + abortedTask);
			}*/
		}
	}
	
	private static void handleRequest(Socket connection) {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		/*try {
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
		}*/
		
	}
	
}
