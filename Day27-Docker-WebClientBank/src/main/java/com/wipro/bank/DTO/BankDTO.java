package com.wipro.bank.DTO;

public class BankDTO {

	private int accountId;

	private String holder_name;

	private String holder_phone;

	private double balance;

	private String vendor_name;

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getHolder_name() {
		return holder_name;
	}

	public void setHolder_name(String holder_name) {
		this.holder_name = holder_name;
	}

	public String getHolder_phone() {
		return holder_phone;
	}

	public void setHolder_phone(String holder_phone) {
		this.holder_phone = holder_phone;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getVendor_name() {
		return vendor_name;
	}

	public void setVendor_name(String vendor_name) {
		this.vendor_name = vendor_name;
	}

	public BankDTO(int accountId, String holder_name, String holder_phone, double balance, String vendor_name) {
		super();
		this.accountId = accountId;
		this.holder_name = holder_name;
		this.holder_phone = holder_phone;
		this.balance = balance;
		this.vendor_name = vendor_name;
	}
	
	public BankDTO() {
		
	}

}
