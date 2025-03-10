package com.wipro.vendor.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wipro.vendor.model.Vendor;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Integer> {
	@Query("SELECT v FROM Vendor v")
	List<Vendor> getAllVendors();
	
	
	@Query("SELECT v FROM Vendor v WHERE v.vendorAbbri =:vendorAbbri")
	Optional<Vendor> findByVendorAbbri(String vendorAbbri);
}
