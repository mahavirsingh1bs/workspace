package com.sapient.global.thread;

public class Philosopher extends Thread {
	private String name;
	private ChopStick leftStick;
	private ChopStick rightStick;
	
	private Philosopher(String name, ChopStick leftStick, ChopStick rightStick) {
		this.name = name;
		this.leftStick = leftStick;
		this.rightStick = rightStick;
	}
	
	public static Philosopher getInstance(String name, ChopStick leftStick, ChopStick rightStick) {
		return new Philosopher(name, leftStick, rightStick);
	}
	
	public void run() {
		System.out.println(name + " is thinking");
		System.out.println(name + " waiting for left " + leftStick.getName());
		synchronized (leftStick) {
			System.out.println(name + " picked up left " + leftStick.getName());
			System.out.println(name + " waiting for right " + rightStick.getName());
			synchronized (rightStick) {
				System.out.println(name + " picked up right " + rightStick.getName());
				System.out.println(name + " eating food");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.out.println(name + " putting down right " + rightStick.getName());
			}
			System.out.println(name + " putting down left " + leftStick.getName());
		}
	}

}
