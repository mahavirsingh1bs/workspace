package com.sapient.global.serialize;

import java.util.Calendar;
import java.util.Date;

public class PersistentTime {
	private Date time;

	public PersistentTime() {
		time = Calendar.getInstance().getTime();
	}

	public Date getTime() {
		return time;
	}

}
