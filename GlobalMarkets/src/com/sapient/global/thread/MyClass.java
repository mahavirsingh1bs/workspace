package com.sapient.global.thread;

public class MyClass extends Thread {
	private String name;
	private MyObject myObj;
	
	public MyClass(MyObject obj, String name) {
		this.myObj = obj;
		this.name = name;
	}
	
	public void run() {
		// myObj.foo(name);
	}
	

}
