package com.sapient.global.thread;

public class Producer extends Thread {
	private ValueObject valueObj;
	private int number;
	
	public Producer(ValueObject valueObj, int number) {
		this.valueObj = valueObj;
		this.number = number;
	}
	
	public void run() {
		for (int i = 1; i <= 10; i++) {
			valueObj.put(i);
			System.out.println("Publiser " + number + " put value " + i);
			try {
				sleep((int)(Math.random() * 100));
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}
}
