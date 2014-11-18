package com.sapient.gm.ubs;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FactorialTest {

	public static void main(String...args) {
		ExecutorService exec = Executors.newFixedThreadPool(4);
		List<Future<Integer>> futures = new LinkedList<Future<Integer>>();
		
		for (int i = 100; i > 0; i = i - 10) {
			futures.add(exec.submit(new FactorialCallable(i % 25)));
		}
		
		for (Future<Integer> future : futures) {
			try {
				System.out.println(future.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	public static class FactorialCallable implements Callable<Integer> {
		private int number;
		
		public FactorialCallable(int number) {
			this.number = number;
		}
		
		@Override
		public Integer call() throws Exception {
			System.out.println(number);
			if (number == 0) {
				throw new IllegalArgumentException("Illegal number");
			}
			return (int)Math.random();
		}
		
	} 
}
