package com.sapient.gm.prodcons;

public class Producer extends Thread {
	private CubbyHole cubbyHole;
	private int number;
	
	public Producer(CubbyHole c, int number) {
		this.cubbyHole = c;
		this.number = number;
	}
	
	public void run() {
		for (int i = 1; i <= 10; i++) {
			cubbyHole.put(i);
			System.out.println("Producer #" + this.number + " put: " + i);
			try {
				sleep((int)(Math.random() * 100));
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}

}
