package com.wipro.customer.DTO;

public class VendorDTO {
	
	private int vendorId;
	private String vendorName;
	private String vendorAbbri;
	
	
	public int getVendorId() {
		return vendorId;
	}
	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public String getVendorAbbri() {
		return vendorAbbri;
	}
	public void setVendorAbbri(String vendorAbbri) {
		this.vendorAbbri = vendorAbbri;
	}
	
	public VendorDTO(int vendorId, String vendorName, String vendorAbbri) {
		super();
		this.vendorId = vendorId;
		this.vendorName = vendorName;
		this.vendorAbbri = vendorAbbri;
	}
	
	public VendorDTO() {
		
	}

}
