package com.wipro.bank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wipro.bank.model.Bank;

@SuppressWarnings("unused")
@Repository
public interface BankRepository extends JpaRepository<Bank, Integer> {
	@Query("SELECT c FROM Bank c")
	List<Bank> getAllBankUsers();
}
