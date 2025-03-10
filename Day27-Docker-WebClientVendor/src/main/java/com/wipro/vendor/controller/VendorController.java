package com.wipro.vendor.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.wipro.vendor.DTO.VendorDTO;
import com.wipro.vendor.model.Vendor;
import com.wipro.vendor.service.VendorService;

import java.util.List;

@RestController
@RefreshScope
@RequestMapping("vendor")
@Tag(name = "Vendor Management System", description = "Operations related to Vendors")
public class VendorController {

	@Autowired
	private VendorService vendorService;

	@PostMapping("/add")
	@Operation(summary = "Add a new Vendor", description = "Adds a new vendor record to the database")
	public ResponseEntity<Vendor> addVendor(@RequestBody Vendor newVendor) {
		Vendor vendor = new Vendor();
		vendor.setVendorName(newVendor.getVendorName());
		vendor.setVendorAbbri(newVendor.getVendorAbbri());
		
		Vendor savedVendor = vendorService.addVendor(vendor);
		return new ResponseEntity<>(savedVendor, HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
    @Operation(summary = "View all Vendors", description = "Displays all Vendor records")
    public List<Vendor> getAllVendors(){
		return vendorService.getAllVendors();
	}
	
	/*
	 * @GetMapping("/{id}")
	 * 
	 * @Operation(summary = "View vendor with ID", description =
	 * "Displays vendor records with ID x") public ResponseEntity<VendorDTO>
	 * getVendor(@PathVariable int id){ VendorDTO bankDTO =
	 * vendorService.getVendorById(id); return ResponseEntity.ok(bankDTO); }
	 */
	
	@GetMapping("/{vendorAbbri}") // Ensure it accepts a String
    public ResponseEntity<VendorDTO> getVendorByAbbri(@PathVariable String vendorAbbri) {
        VendorDTO vendorDTO = vendorService.getVendorByAbbri(vendorAbbri);
        return ResponseEntity.ok(vendorDTO);
    }
	
	@PutMapping("/update/{id}")
    @Operation(summary = "Update vendor", description = "Updates a vendor with given ID")
    public ResponseEntity<Vendor> updateVendor(@PathVariable int id, @RequestBody Vendor updatedVendor){
		Vendor vendor = vendorService.updateVendor(id, updatedVendor);
		return ResponseEntity.ok(vendor);
	}
	
	@DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete vendor", description = "Deletes a Vendor with given ID")
    public ResponseEntity<String> deleteVendor(@PathVariable int id){
		vendorService.deleteVendor(id);
		return ResponseEntity.ok("Vendor with ID "+id+" has been deleted.");
	}
	
}
