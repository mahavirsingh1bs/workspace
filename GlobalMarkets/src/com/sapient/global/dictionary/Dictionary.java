package com.sapient.global.dictionary;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Dictionary {
	private final ReentrantReadWriteLock readWriteLock = 
			new ReentrantReadWriteLock();
	private final Lock read = readWriteLock.readLock();
	private final Lock write = readWriteLock.writeLock();
	private HashMap<String, String> dictionary = new HashMap<String, String>();
	
	public void set(String key, String value) {
		write.lock();
		try {
			dictionary.put(key, value);
		} finally {
			write.unlock();
		}
	}
	
	public String get(String key) {
		read.lock();
		try {
			return dictionary.get(key);
		} finally {
			read.unlock();
		}
	}
	
	public String[] getKeys() {
		read.lock();
		try {
			String keys[] = new String[dictionary.size()];
			return dictionary.keySet().toArray(keys);
		} finally {
			read.unlock();
		}
	}
	
	public static void main(String...args) {
		Dictionary dictionary = new Dictionary();
		dictionary.set("java", "object oriented");
		dictionary.set("linux", "rulez");
		/*Writer writer = new Writer(dictionary, "Mr. Writer");
		Reader reader1k = new Reader(dictionary, "Mr. Reader1");
		Reader reader2 = new Reader(dictionary, "Mr. Reader2");
		Reader reader3 = new Reader(dictionary, "Mr. Reader3");
		Reader reader4 = new Reader(dictionary, "Mr. Reader4");
		Reader reader5 = new Reader(dictionary, "Mr. Reader5");*/
	}
}
