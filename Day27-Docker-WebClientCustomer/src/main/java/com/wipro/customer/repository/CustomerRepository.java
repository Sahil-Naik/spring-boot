package com.wipro.customer.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wipro.customer.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	@Query("SELECT c FROM Customer c")
	List<Customer> getAllCustomers();
	
	@Query("SELECT c FROM Customer c WHERE c.customerBankId =:bankId")
	Optional<Customer> findByBankId(String bankId);
	
	@Query("SELECT c FROM Customer c WHERE c.customerBill > :amount")
	List<Customer> findCustomersByBillGreaterThan( @Param("amount") double amount);
	
	@Query("SELECT c FROM Customer c WHERE c.customerBill < :amount")
	List<Customer> findCustomersByBillLessThan(@Param("amount") double amount);
}
