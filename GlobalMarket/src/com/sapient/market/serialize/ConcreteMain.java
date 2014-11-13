package com.sapient.market.serialize;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author mahavir.singh
 *
 */
public class ConcreteMain {

	public static void main(String... args) throws Exception, IOException {
		File f = new File("a.txt");
		ConcretePage cp = new ConcretePage("Ankita", "Atul");

		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(f));
		out.writeObject(cp);

		ObjectInputStream in = new ObjectInputStream(new FileInputStream(f));
		cp = (ConcretePage) in.readObject();
		System.out.println("After deserialize user is: " + cp.getUser()
				+ " and author is:" + cp.getAuthor());
	}
}
