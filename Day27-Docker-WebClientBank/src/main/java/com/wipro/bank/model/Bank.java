package com.wipro.bank.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;

@Entity
@Table(name="bank")
public class Bank {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int accountId;
	
	@NotBlank(message = "Account holder name cannot be Blank.")
	private String holder_name;
	
	@NotBlank(message = "Account holder phone number cannot be Blank.")
	private String holder_phone;
	
	@NotNull(message = "Balance cannot be Null.")
    @Min(value = 0, message = "Balance must be greater than 0.")
	private double balance;
	
	@NotBlank(message = "Account Vendor name cannot be Blank.")
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

	public Bank(int accountId, String holder_name, String holder_phone, double balance, String vendor_name) {
		super();
		this.accountId = accountId;
		this.holder_name = holder_name;
		this.holder_phone = holder_phone;
		this.balance = balance;
		this.vendor_name = vendor_name;
	}
	
	public Bank() {
		
	}

}
