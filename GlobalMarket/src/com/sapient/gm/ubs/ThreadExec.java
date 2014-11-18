package com.sapient.gm.ubs;

public class ThreadExec {
	private static ThreadExec INSTANCE = null;
	
	private ThreadExec() { }
	
	public static ThreadExec getInstance() {
		if (INSTANCE == null) {
			synchronized (ThreadExec.class) {
				INSTANCE = new ThreadExec();
			}
		}
		return INSTANCE;
	}
}
