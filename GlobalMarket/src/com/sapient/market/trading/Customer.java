package com.sapient.market.trading;

public class Customer extends Thread {
	private CarParking carParking;
	private String name;
	
	public Customer(CarParking parking, String name) {
		this.carParking = parking;
		this.name = name;
	}
	
	public void run() {
		Car hiredCar = carParking.hireCar(name);
		if (hiredCar != null) {
			// hiredCar.makeARound();
			System.out.println(name + " making a round");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
			carParking.returnCar(hiredCar, name);
			System.out.println();
		} else {
			System.out.println("Right now, no available car");
		}
	}
	
	
}
