package com.wipro.bank.service;

import com.wipro.bank.exception.ResourceNotFoundException;
import com.wipro.bank.DTO.BankDTO;
import com.wipro.bank.model.Bank;
import com.wipro.bank.repository.BankRepository;

import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import java.util.List;
import java.util.Optional;

@Service
@RefreshScope
public class BankService {

	@Autowired
    private BankRepository bankRepository;
	
	public Bank addBankUser(Bank bank) {
		return bankRepository.save(bank);
	}
	
	public List<Bank> getAllBankUsers(){
		return bankRepository.findAll();
	}
	
	public BankDTO getBankUserById(int id) {
		Bank bank = bankRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Bank user with ID "+id+" not found."));
		
		return new BankDTO(bank.getAccountId(),
				bank.getHolder_name(),
				bank.getHolder_phone(),
				bank.getBalance(),
				bank.getVendor_name());
	}
	
	public Bank updateBankUser(int id, Bank updatedBankUser) {
		Optional<Bank> exisitingBankUser = bankRepository.findById(id);
		if (exisitingBankUser.isPresent()) {
			Bank bank = exisitingBankUser.get();
			bank.setHolder_name(updatedBankUser.getHolder_name());
			bank.setHolder_phone(updatedBankUser.getHolder_phone());
			bank.setBalance(updatedBankUser.getBalance());
			bank.setVendor_name(updatedBankUser.getVendor_name());
			return bankRepository.save(bank);
		}
		throw new ResourceNotFoundException("Bank user with id "+id+" not found.");
	}
	
	public void deleteBankUser(int id) {
		if (!bankRepository.existsById(id)) {
			throw new ResourceNotFoundException("Bank user with id "+id+" not found.");
		}
		bankRepository.deleteById(id);
	}
}
