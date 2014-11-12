package com.sapient.global.thread;

public class ValueObject {
	private int value;
	private boolean available = false;
	
	public synchronized int get() {
		while (available == true) {
			try {
				wait();
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
		available = false;
		notifyAll();
		return value;
	}
	
	public synchronized void put(int value) {
		while (available != false) {
			try {
				wait();
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
		this.value = value;
		available = true;
		notifyAll();
	}
}
