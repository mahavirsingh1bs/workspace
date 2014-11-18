package com.sapient.global.serialize;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ObjectWritter {

	public static void main(String args[]) throws IOException {
		FileOutputStream fos = new FileOutputStream("temp.out");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		SerialTest ts = new SerialTest();
		oos.writeObject(ts);
		oos.flush();
		oos.close();
	}
}
