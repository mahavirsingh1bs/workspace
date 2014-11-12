package com.sapient.market.trading;

import java.util.ArrayList;
import java.util.List;

public class ZorasicPark {
	
	public static void main(String...args) {
		CarParking parking = new CarParking(10);
		
		List<Customer> customers = new ArrayList<>();
		
		for (int i = 1; i <= 20; i++) {
			customers.add(new Customer(parking, "Customer " + i));
		}
		
		for (int i = 1; i < 20; i++) {
			Customer customer = customers.get(i);
			customer.start();
			if (i % 13 == 0) {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}
			
		}
		
	}
	
}
