package com.sapient.global.thread;

public class Consumer extends Thread {
	private ValueObject valueObj;
	private int number;
	
	public Consumer(ValueObject valueObj, int number) {
		this.valueObj = valueObj;
		this.number = number;
	}
	
	public void run() {
		for (int i = 0; i < 10; i++) {
			int value = valueObj.get();
			System.out.println("Consumer " + number + " get value " + value);
		}
	}
}
