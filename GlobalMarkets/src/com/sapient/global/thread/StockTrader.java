package com.sapient.global.thread;

public class StockTrader {
	
	public static void main(String...args) {
		StockRegistry registry = new StockRegistry();
		StockService stockService = new StockService(registry);
		Thread stockServiceThread = new Thread(stockService);
		
		stockServiceThread.start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		
		Stock yStock = new Stock("YAH", "Yahoo", StockTrend.STOCKUP, 132);
		Stock gStock = new Stock("GOO", "Google", StockTrend.STOCKDOWN, 128);
		
		stockService.addStock(yStock);
		stockService.addStock(gStock);
		
		System.out.println(stockService.getLength());
		System.out.println(stockService.getStocks());
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		
		Stock sStock = new Stock("SAP", "Sapient", StockTrend.STOCKUP, 132);
		Stock rStock = new Stock("RBS", "RBS", StockTrend.STOCKDOWN, 128);
		
		stockService.addStock(sStock);
		stockService.addStock(rStock);
		
		System.out.println(stockService.getStocks());
	}
}
