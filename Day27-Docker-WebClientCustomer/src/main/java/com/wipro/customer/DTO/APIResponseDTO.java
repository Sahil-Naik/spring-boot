package com.wipro.customer.DTO;

import lombok.Data;

@Data
public class APIResponseDTO {
	
	private CustomerDTO customerDTO;
    private BankDTO bankDTO;
    private VendorDTO vendorDTO;
    
	public CustomerDTO getCustomerDTO() {
		return customerDTO;
	}
	public void setCustomerDTO(CustomerDTO customerDTO) {
		this.customerDTO = customerDTO;
	}
	public BankDTO getBankDTO() {
		return bankDTO;
	}
	public void setBankDTO(BankDTO bankDTO) {
		this.bankDTO = bankDTO;
	}	
	public VendorDTO getVendorDTO() {
		return vendorDTO;
	}
	public void setVendorDTO(VendorDTO vendorDTO) {
		this.vendorDTO = vendorDTO;
	}
	
	public APIResponseDTO(CustomerDTO customerDTO, BankDTO bankDTO, VendorDTO vendorDTO) {
		super();
		this.customerDTO = customerDTO;
		this.bankDTO = bankDTO;
		this.vendorDTO = vendorDTO;
	}
	
	public APIResponseDTO() {
		
	}	

}
