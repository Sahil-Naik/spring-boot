package com.wipro.vendor.service;

import com.wipro.vendor.exception.ResourceNotFoundException;
import com.wipro.vendor.DTO.VendorDTO;
import com.wipro.vendor.model.Vendor;
import com.wipro.vendor.repository.VendorRepository;

import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import java.util.List;
import java.util.Optional;

@Service
@RefreshScope
public class VendorService {
	
	@Autowired
	private ModelMapper mapper;

	@Autowired
    private VendorRepository vendorRepository;
	
	public Vendor addVendor(Vendor vendor) {
		return vendorRepository.save(vendor);
	}
	
	public List<Vendor> getAllVendors(){
		return vendorRepository.findAll();
	}
	
	public VendorDTO getVendorById(int id) {
		Vendor vendor = vendorRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Bank user with ID "+id+" not found."));
		
		return new VendorDTO(vendor.getVendor_id(),
				vendor.getVendorName(),
				vendor.getVendorAbbri());
	}
	
	public VendorDTO getVendorByAbbri(String vendor_abbri) {
        Vendor vendor = vendorRepository.findByVendorAbbri(vendor_abbri)
                .orElseThrow(() -> new ResourceNotFoundException("Vendor with abbreviation " + vendor_abbri + " not found."));
        
        return new VendorDTO(vendor.getVendor_id(),
				vendor.getVendorName(),
				vendor.getVendorAbbri());
    }
	
	public Vendor updateVendor(int id, Vendor updatedVendor) {
		Optional<Vendor> exisitingVendor = vendorRepository.findById(id);
		if (exisitingVendor.isPresent()) {
			Vendor vendor = exisitingVendor.get();
			vendor.setVendorName(updatedVendor.getVendorName());
			vendor.setVendorAbbri(updatedVendor.getVendorAbbri());
			return vendorRepository.save(vendor);
		}
		throw new ResourceNotFoundException("Vendor with id "+id+" not found.");
	}
	
	public void deleteVendor(int id) {
		if (!vendorRepository.existsById(id)) {
			throw new ResourceNotFoundException("Vendor with id "+id+" not found.");
		}
		vendorRepository.deleteById(id);
	}
}
