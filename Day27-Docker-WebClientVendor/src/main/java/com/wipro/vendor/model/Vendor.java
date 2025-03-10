package com.wipro.vendor.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "vendor")
public class Vendor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int vendor_id;

	@Column(name = "vendor_name")
	private String vendorName;

	@Column(name = "vendor_abbri")
	private String vendorAbbri;

	public int getVendor_id() {
		return vendor_id;
	}

	public void setVendor_id(int vendor_id) {
		this.vendor_id = vendor_id;
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

	public Vendor(int vendor_id, String vendorName, String vendorAbbri) {
		super();
		this.vendor_id = vendor_id;
		this.vendorName = vendorName;
		this.vendorAbbri = vendorAbbri;
	}

	public Vendor() {

	}
}
