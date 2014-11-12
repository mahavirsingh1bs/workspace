package com.sapient.global.thread;

public class ProducerConsumer {
	
	public static void main(String...args) {
		ValueObject valueObj = new ValueObject();
		Producer p1 = new Producer(valueObj, 1);
		Consumer c1 = new Consumer(valueObj, 1);
		p1.start();
		c1.start();
	}
}
