package com.sapient.global.thread;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class StockRegistry {
	private List<Stock> stocks = new LinkedList<Stock>();
	
	public void addStock(Stock stock) {
		stocks.add(stock);
	}
	
	public boolean removeStock(Stock stock) {
		return stocks.remove(stock);
	}
	
	public List<Stock> getStocks() {
		return Collections.unmodifiableList(stocks);
	}
	
	public int getTotal() {
		return stocks.size();
	}
}
