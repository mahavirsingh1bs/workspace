package com.sapient.market.serialize;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ConcretePage implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String user;
	private transient String author;
	
	public ConcretePage(String user, String author) {
		System.out.println("Constructor called");
		this.user = user;
		this.author = author;
	}
	
	private void writeObject(ObjectOutputStream out) throws IOException {
		System.out.println("Write Object called");
        out.defaultWriteObject();
        out.writeObject(this.author);
    }
 
    private void readObject(ObjectInputStream in) throws IOException,ClassNotFoundException {
    	System.out.println("Read Object called");
        in.defaultReadObject();
        this.author = (String)in.readObject();
    }

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
    
}
