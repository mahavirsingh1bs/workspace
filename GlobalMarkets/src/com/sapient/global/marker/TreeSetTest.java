package com.sapient.global.marker;

import java.util.HashSet;
import java.util.Set;

public class TreeSetTest {
	
	public static void main(String...args) {
		Employee e1 = new Employee();
		Employee e2 = new Employee();
		Employee e3 = new Employee();
		Employee e4 = new Employee();
		Set<Employee> emps = new HashSet<Employee>();
		emps.add(e1); emps.add(e2);
		emps.add(e3); emps.add(e4);
		System.out.println(emps);
		
		Set<Employee> empSorted = new HashSet<Employee>();
		empSorted.add(e1); empSorted.add(e2);
		empSorted.add(e3); empSorted.add(e4);
		System.out.println(empSorted);
	}
}
