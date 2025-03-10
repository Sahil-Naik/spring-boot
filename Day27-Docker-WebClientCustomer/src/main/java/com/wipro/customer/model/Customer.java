package com.wipro.customer.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;

@Entity
@Table(name="customer")
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId; // customer_id
	
    private String customerPhone; // customer_phone
	
    @NotNull(message = "Bill cannot be Null.")
    @Min(value = 0, message = "Bill must be greater than 0.")
    private String customerBill; // customer_bill
    
    @NotBlank(message = "Customer account ID cannot be Blank.")
    private String customerBankId; // customer_bank_id
    
    @NotBlank(message = "Customer Store cannot be Blank.")
    private String customerStore; // customer_store

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getCustomerBill() {
		return customerBill;
	}

	public void setCustomerBill(String customerBill) {
		this.customerBill = customerBill;
	}

	public String getCustomerBankId() {
		return customerBankId;
	}

	public void setCustomerBankId(String customerBankId) {
		this.customerBankId = customerBankId;
	}

	public String getCustomerStore() {
		return customerStore;
	}

	public void setCustomerStore(String customerStore) {
		this.customerStore = customerStore;
	}

	public Customer(int customerId, String customerPhone,
			@NotNull(message = "Bill cannot be Null.") @Min(value = 0, message = "Bill must be greater than 0.") String customerBill,
			@NotBlank(message = "Customer account ID cannot be Blank.") String customerBankId,
			@NotBlank(message = "Customer Store cannot be Blank.") String customerStore) {
		super();
		this.customerId = customerId;
		this.customerPhone = customerPhone;
		this.customerBill = customerBill;
		this.customerBankId = customerBankId;
		this.customerStore = customerStore;
	}
    
    public Customer() {
    	
    }
}