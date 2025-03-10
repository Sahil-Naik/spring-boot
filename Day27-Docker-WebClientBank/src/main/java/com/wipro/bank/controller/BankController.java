package com.wipro.bank.controller;

import com.wipro.bank.DTO.*;
import com.wipro.bank.model.*;
import com.wipro.bank.service.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RefreshScope
@RequestMapping("bank")
@Tag(name = "Bank Management System", description = "Operations related to banking")
public class BankController {

	@Autowired
	private BankService bankService;

	@PostMapping("/add")
	@Operation(summary = "Add a new bank user", description = "Adds a new bank user record to the database")
	public ResponseEntity<Bank> addBankUser(@RequestBody Bank newBankUser) {
		Bank bank = new Bank();
		bank.setHolder_name(newBankUser.getHolder_name());
		bank.setHolder_phone(newBankUser.getHolder_phone());
		bank.setBalance(newBankUser.getBalance());
		bank.setVendor_name(newBankUser.getVendor_name());
		
		Bank savedBankUser = bankService.addBankUser(bank);
		return new ResponseEntity<>(savedBankUser, HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
    @Operation(summary = "View all Bank users", description = "Displays all Bank users records")
    public List<Bank> getAllBankUsers(){
		return bankService.getAllBankUsers();
	}
	
	@GetMapping("/{id}")
    @Operation(summary = "View Bank user with ID", description = "Displays Customer records with ID x")
	public ResponseEntity<BankDTO> getBankUser(@PathVariable int id){
		BankDTO bankDTO = bankService.getBankUserById(id);
		return ResponseEntity.ok(bankDTO);
	}
	
	@PutMapping("/update/{id}")
    @Operation(summary = "Update Bank user", description = "Updates a bank user with given ID")
    public ResponseEntity<Bank> updateBankUser(@PathVariable int id, @RequestBody Bank updatedBankUser){
		Bank bank = bankService.updateBankUser(id, updatedBankUser);
		return ResponseEntity.ok(bank);
	}
	
	@DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete Customer", description = "Deletes a Customer with given ID")
    public ResponseEntity<String> deleteBankUser(@PathVariable int id){
		bankService.deleteBankUser(id);
		return ResponseEntity.ok("Bank user with ID "+id+" has been deleted.");
	}
	
}
