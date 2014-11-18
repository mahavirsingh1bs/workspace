package com.sapient.market.trading;

import java.io.File;

import javax.xml.bind.DatatypeConverter;

public class InstanceCallbackDigestUserInterface {
	private String filename;
	private byte[] digest;
	
	public InstanceCallbackDigestUserInterface(String filename) {
		this.filename = filename;
	}
	
	public void calculateDigest() {
		InstanceCallableDigest cb = new InstanceCallableDigest(filename, this);
		Thread t = new Thread(cb);
		t.start();
	}
	
	public void receiveDigest(byte[] digest) {
		this.digest = digest;
		System.out.println(this);
	}

	@Override
	public String toString() {
		String result = filename + ": ";
		if (digest != null) {
			result += DatatypeConverter.printHexBinary(digest);
		} else {
			result += "digest not available";
		}
		return result;
	}
	
	public static void main(String...args) {
		System.out.println(new File("").getAbsolutePath());
		for (String filename : args) {
			InstanceCallbackDigestUserInterface d = 
					new InstanceCallbackDigestUserInterface(filename);
			d.calculateDigest();
		}
	}
	
}
