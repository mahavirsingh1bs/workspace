package com.sapient.global.serialize;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class EmployeeUtil {
	
	public static void serialize(Employee emp) {
		System.out.println("Serializing employee");
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("employee.ser"));
			out.writeObject(emp);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Employee deserialize() {
		System.out.println("Deserializing employee");
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("employee.ser"));
			Employee emp = (Employee) in.readObject();
			in.close();
			return emp;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
