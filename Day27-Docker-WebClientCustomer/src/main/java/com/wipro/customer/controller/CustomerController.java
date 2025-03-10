package com.wipro.customer.controller;

import com.wipro.customer.DTO.APIResponseDTO;
import com.wipro.customer.DTO.CustomerDTO;
import com.wipro.customer.model.Customer;
import com.wipro.customer.service.CustomerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RefreshScope
@RequestMapping("customer")
@Tag(name = "Customer-Bill Management System", description = "Operations related to handling Customer")
public class CustomerController {
    
    @Autowired
    private CustomerService customerService;
    
    @PostMapping("/add")
    @Operation(summary = "Add a new Customer", description = "Adds a new Customer record to the database")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer newCustomer) {
        Customer customer = new Customer();
        customer.setCustomerPhone(newCustomer.getCustomerPhone());
        customer.setCustomerBill(newCustomer.getCustomerBill());
        customer.setCustomerBankId(newCustomer.getCustomerBankId());
        customer.setCustomerStore(newCustomer.getCustomerStore());
        
        Customer savedCustomer = customerService.addCustomer(customer);
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }
    
    @GetMapping("/all")
    @Operation(summary = "View all Customers", description = "Displays all Customer records")
    public List<Customer> getAllCustomer() {
        return customerService.getAllCustomers();
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "View Customer with ID", description = "Displays Customer records with ID x")
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "Found the Customer", 
            content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = Customer.class)) }),
        @ApiResponse(responseCode = "400", description = "Invalid id supplied", 
            content = @Content), 
        @ApiResponse(responseCode = "404", description = "Customer not found", 
            content = @Content) 
    })
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable int id) {
        CustomerDTO custDTO = customerService.getCustomerById(id);
        return ResponseEntity.ok(custDTO);
    }
    
    @GetMapping("/bankid-{bankId}")
    @Operation(summary = "View Customer with BankID", description = "Displays Customer records with Bank-ID x")
    public ResponseEntity<APIResponseDTO> getCustomerByBankId(@PathVariable String bankId) {
        APIResponseDTO custDTO = customerService.getCustomerByBankId(bankId);
        return ResponseEntity.ok(custDTO);
    }
    
    @GetMapping("/bill-greater-than")
    @Operation(summary = "Customer with bill greater than", description = "Displays Customer record having Bill greater than x")
    public List<Customer> getCustomersByBillGreaterThan(@RequestParam double amount) {
        return customerService.getCustomersByBillGreaterThan(amount);
    }
    
    @GetMapping("/bill-less-than")
    @Operation(summary = "Customer with bill less than", description = "Displays Customer record having Bill less than x")
    public List<Customer> getCustomersByBillLessThan(@RequestParam double amount) {
        return customerService.getCustomersByBillLessThan(amount);
    }
    
    @GetMapping("/paginate")
    @Operation(summary = "Paged results", description = "Returns all customers in paged manner")
    public Page<Customer> getCustomers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "customerPhone") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {
        return customerService.getCustomersPaged(page, size, sortBy, direction);
    }
    
    @PutMapping("/update/{id}")
    @Operation(summary = "Update Customer", description = "Updates a Customer with given ID")
    public ResponseEntity<Customer> updateCustomer(@PathVariable int id, @RequestBody Customer updatedCustomer) {
        Customer customer = customerService.updateCustomer(id, updatedCustomer);
        return ResponseEntity.ok(customer);
    }
    
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete Customer", description = "Deletes a Customer with given ID")
    public ResponseEntity<String> deleteCustomer(@PathVariable int id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok("Customer with ID " + id + " has been deleted.");
    }
}
