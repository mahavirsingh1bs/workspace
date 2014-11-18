package com.sapient.global.thread;

import java.util.ArrayList;
import java.util.List;

public class DiningPhilosopher {
	private List<Philosopher> philosophers = new ArrayList<Philosopher>();
	private List<ChopStick> chopSticks = new ArrayList<ChopStick>();
	
	public static void main(String...args) {
		DiningPhilosopher diningPhilosopher = new DiningPhilosopher();
		diningPhilosopher.prepareDiningTable(2);
		for (Philosopher philosopher : diningPhilosopher.philosophers) {
			philosopher.start();
		}
	}
	
	public void prepareDiningTable(int noOfPhilosophers) {
		for (int i = 0; i < noOfPhilosophers; i++) {
			chopSticks.add(new ChopStick("Chop Stick " + i));
		}
		
		for (int i = 0; i < noOfPhilosophers; i++) {
			ChopStick leftStick = chopSticks.get(i);
			ChopStick rightStick = null;
			if ((i % (noOfPhilosophers - 1)) != 0) {
				rightStick = chopSticks.get(i + 1);
			} else {
				rightStick = chopSticks.get(i % (noOfPhilosophers - 1));
			}
			philosophers.add(Philosopher.getInstance("Philosopher " + i, leftStick, rightStick));
		}
	}
	
	
}
