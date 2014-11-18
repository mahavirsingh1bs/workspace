package com.sapient.global.serialize;

import java.util.Date;

public class Employee extends Person {
	private static final long serialVersionUID = 1L;
	private int empId;
	private Date dateOfBirth;
	
	public Employee() {
		// super(null, null);
		System.out.println("Default Cosn");
	}
	
	public Employee(String firstName, String lastName, 
			int empId, Date dateOfBirth) {
		super(firstName, lastName);
		this.empId = empId;
		this.dateOfBirth = dateOfBirth;
	}
	
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", dateOfBirth=" + dateOfBirth
				+ ", toString()=" + super.toString() + "]";
	}
	
}
