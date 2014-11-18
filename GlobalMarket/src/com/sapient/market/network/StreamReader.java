package com.sapient.market.network;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class StreamReader {

	public static void main(String... args) {
		try {
			URL u = new URL("http://www.amazon.com");
			try (InputStream in = u.openStream()) {
				int c;
				while ((c = in.read()) != -1)
					System.out.write(c);
			}
		} catch (IOException ex) {
			System.err.println(ex);
		}
	}
}
