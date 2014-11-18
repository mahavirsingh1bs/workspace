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

		ObjectInputStream in1 = new ObjectInputStream(new FileInputStream(f));
		ConcretePage cp1 = (ConcretePage) in1.readObject();
		in1.close();
		System.out.println("After deserialize user is: " + cp.getUser()
				+ " and author is:" + cp.getAuthor());
		
		ObjectInputStream in2 = new ObjectInputStream(new FileInputStream(f));
		ConcretePage cp2 = (ConcretePage) in2.readObject();
		System.out.println(cp == cp1);
		System.out.println(cp1 == cp2);
	}
}
