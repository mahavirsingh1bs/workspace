package com.sapient.market.trading;

import java.util.ArrayDeque;
import java.util.Queue;

public class CarParking {
	private Queue<Car> parkedCars = new ArrayDeque<>();
	// private int cars;
	
	public CarParking(int cars) {
		for (int i = 1; i <= cars; i++) {
			parkedCars.add(new Car("Car " + i));
		}
		//this.cars = cars;
	}
	
	public Car hireCar(String customer) {
		synchronized (parkedCars) {
			if (parkedCars.size() > 0) {
				Car car = parkedCars.poll();
				System.out.println(car.getName() + " hired by " + customer);
				System.out.println("Remaining cars are: " + parkedCars.size());
				return car;
			} else {
				return null;
			}
		}
	}
	
	public void returnCar(Car car, String customer) {
		synchronized (parkedCars) {
			parkedCars.add(car);
			System.out.println(car.getName() + " returned by " + customer);
			System.out.println("Available cars are: " + parkedCars.size());
		}
	}
	
}
