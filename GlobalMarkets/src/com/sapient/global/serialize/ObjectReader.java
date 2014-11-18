package com.sapient.global.serialize;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ObjectReader {

	public static void main(String args[]) throws IOException {
		FileInputStream fis = new FileInputStream("temp.out");
		ObjectInputStream oin = new ObjectInputStream(fis);
		SerialTest ts = null;
		try {
			ts = (SerialTest) oin.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("version="+ts.version);
	}
}
