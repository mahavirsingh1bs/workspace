package com.sapient.global.thread;

import java.util.List;

public class StockService implements Runnable {
	private final StockRegistry stockRegister;
	
	public StockService(StockRegistry stockRegister) {
		this.stockRegister = stockRegister;
	}

	@Override
	public void run() {
		while (true) {
			System.out.println("Waiting for stocks!");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public void addStock(Stock stock) {
		synchronized (stockRegister) {
			System.out.println("Adding stock " + stock.getName());
			stockRegister.addStock(stock);
		}
	}
	
	public void removeStock(Stock stock) {
		stockRegister.removeStock(stock);
	}
	
	public List<Stock> getStocks() {
		return stockRegister.getStocks();
	}
	
	public int getLength() {
		return stockRegister.getTotal();
	}
}
