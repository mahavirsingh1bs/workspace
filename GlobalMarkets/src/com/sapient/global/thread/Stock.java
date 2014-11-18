package com.sapient.global.thread;

public class Stock {
	private String code;
	private String name;
	private StockTrend trend;
	private int value;
	
	public Stock() { }
	
	public Stock(String code, String name, StockTrend trend, int value) {
		super();
		this.code = code;
		this.name = name;
		this.trend = trend;
		this.value = value;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public StockTrend getTrend() {
		return trend;
	}
	public void setTrend(StockTrend trend) {
		this.trend = trend;
	}
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((trend == null) ? 0 : trend.hashCode());
		result = prime * result + value;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stock other = (Stock) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (trend != other.trend)
			return false;
		if (value != other.value)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Stock [code=" + code + ", name=" + name + ", trend=" + trend
				+ ", value=" + value + "]";
	}
	
	
}
