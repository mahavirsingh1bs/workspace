package com.sapient.global.java;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FinallyTest {

	public static void main(String...args) {
			File file = new File("employee.ser");
			try {
				FileInputStream fin = new FileInputStream(file);
				System.out.println("try");
				//System.exit(0);
			} catch (FileNotFoundException e) {
				System.out.println("catch");
				e.printStackTrace();
			} finally {
				System.out.println("Finally");
			}
	}
}
