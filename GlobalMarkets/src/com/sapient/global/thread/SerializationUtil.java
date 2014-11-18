package com.sapient.global.thread;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializationUtil {

	public static void serialize() {
		Employee e = new Employee();
		e.setFirstName("Reyan");
		e.setLastName("Ali");
		e.name = "Reyan Ali";
		e.address = "Phokka Kuan, Ambehta Peer";
		e.SSN = 11122333;
		e.number = 101;
		try {
			File file = new File("./tmp/employee.ser");
			/**
			System.out.println(file.getAbsolutePath());
			if (!file.exists())
				file.createNewFile();
			*/
			FileOutputStream fileOut = new FileOutputStream("./tmp/employee.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(e);
			out.close();
			fileOut.close();
			System.out.printf("Serialized data is saved in /tmp/employee.ser");
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	public static void deserialize() {
		Employee e = null;
		try {
			FileInputStream fileIn = new FileInputStream("./tmp/employee.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			e = (Employee) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			System.out.println("Employee class not found");
			c.printStackTrace();
			return;
		}
		System.out.println("Deserialized Employee...");
		System.out.println("First Name: " + e.getFirstName());
		System.out.println("Last Name: " + e.getLastName());
		System.out.println("Name: " + e.name);
		System.out.println("Address: " + e.address);
		System.out.println("SSN: " + e.SSN);
		System.out.println("Number: " + e.number);
		System.out.println("Department Name: " + e.getDepartment().getName());
		System.out.println("Department Location: " + e.getDepartment().getLocation().getName());
	}

}