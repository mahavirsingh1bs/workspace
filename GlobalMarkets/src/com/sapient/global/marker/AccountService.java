package com.sapient.global.marker;

import java.util.concurrent.TimeUnit;

public class AccountService {
	
	public static void main(String...args) throws InterruptedException {
		Account fromAcct = new Account("Ryan Smith", AccountType.SAVING, "0000987654", "Citi Bank", 500000.0);
		Account toAcct = new Account("John Smith", AccountType.SAVING, "0000987653", "Citi Bank", 100000.0);
		Thread thread1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					transferMoney(fromAcct, toAcct, 100000, 2000, TimeUnit.NANOSECONDS);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		Thread thread2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					transferMoney(fromAcct, toAcct, 100000, 2000, TimeUnit.NANOSECONDS);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		thread1.start(); 
		thread2.start();
		// transferMoney(fromAcct, toAcct, 100000, 2000, TimeUnit.NANOSECONDS);
		Thread.sleep(10000);
		System.out.println("From Account: " + fromAcct);
		System.out.println("To Account: " + toAcct);
	}
	
	public static boolean transferMoney(Account fromAcct, 
								Account toAcct, 
								double amount,
								long timeout, 
								TimeUnit unit) throws InterruptedException {
		if (fromAcct.lock.tryLock(1, TimeUnit.MILLISECONDS)) {
			try {
				if (toAcct.lock.tryLock()) {
					try {
						fromAcct.debit(amount);
						toAcct.credit(amount);
						return true;
					} finally {
						toAcct.lock.unlock();
					}
				}
			} finally {
				fromAcct.lock.unlock();
			}
		}
		return false;
	}

}
