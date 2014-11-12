package com.sapient.global.marker;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.sapient.global.marker.exception.InsufficientBalanceException;

public class Account {
	private String name;
	private AccountType type;
	private String accountNo;
	private String bankName;
	private double balance;
	public Lock lock;
	
	public Account() { }
	
	public Account(String name, AccountType type, String accountNo,
			String bankName, double balance) {
		super();
		this.name = name;
		this.type = type;
		this.accountNo = accountNo;
		this.bankName = bankName;
		this.balance = balance;
		this.lock = new ReentrantLock();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public AccountType getType() {
		return type;
	}
	public void setType(AccountType type) {
		this.type = type;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public void debit(double amount) {
		if (this.balance < amount) {
			throw new InsufficientBalanceException("Insufficient balance");
		}
		this.balance -= amount;
		System.out.println("Debit: " + this.balance);
	}
	
	public void credit(double amount) {
		this.balance += amount;
		System.out.println("Credit " + this.balance);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((accountNo == null) ? 0 : accountNo.hashCode());
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((bankName == null) ? 0 : bankName.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Account other = (Account) obj;
		if (accountNo == null) {
			if (other.accountNo != null)
				return false;
		} else if (!accountNo.equals(other.accountNo))
			return false;
		if (Double.doubleToLongBits(balance) != Double
				.doubleToLongBits(other.balance))
			return false;
		if (bankName == null) {
			if (other.bankName != null)
				return false;
		} else if (!bankName.equals(other.bankName))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Account [name=" + name + ", type=" + type + ", accountNo="
				+ accountNo + ", bankName=" + bankName + ", balance=" + balance
				+ "]";
	}
	
	
	
}
