package com.wipro.customer.DTO;

import lombok.Data;

@Data
public class CustomerDTO {
	
	private int customerId; // customer_id
    private String customerPhone; // customer_phone
    private String customerBill; // customer_bill
    private String customerBankId; // customer_bank_id
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
	
	public CustomerDTO(int customerId, String customerPhone, String customerBill, String customerBankId,
			String customerStore) {
		super();
		this.customerId = customerId;
		this.customerPhone = customerPhone;
		this.customerBill = customerBill;
		this.customerBankId = customerBankId;
		this.customerStore = customerStore;
	}
    
    public CustomerDTO() {
    	
    }

}
