package com.sapient.global.marker;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Computation {

	public static void main(String...args) {
		Lock lock = new ReentrantLock();
		try {
			if (lock.tryLock()) {
				System.out.println("Lock acquired");
			} else {
				System.out.println("Lock cannot be acquired");
			}
		} finally {
			lock.unlock();
		}
	}
}
