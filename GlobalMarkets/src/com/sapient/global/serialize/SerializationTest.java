package com.sapient.global.serialize;

import java.util.Date;

/**
 * @author msin84
 *
 */
public class SerializationTest {

	public static void main(String...args) {
		Employee emp = new Employee("John", "Singh", 112244, new Date(28-07-1986));
		EmployeeUtil.serialize(emp);
		System.out.println(EmployeeUtil.deserialize());
	}
}
