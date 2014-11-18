package com.sapient.global.thread;


public class Employee extends Person {
	public String name;
	public String address;
	public transient int SSN;
	public int number;

	private Department department = new Department("IT");

	public Employee() {
		System.out.println("Default Constructor");
	}
	
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
	public void mailCheck() {
		System.out.println("Mailing a check to " + name + " " + address);
	}
}
