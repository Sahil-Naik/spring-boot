package com.wipro.customer.service;

import com.wipro.customer.exception.ResourceNotFoundException;
import com.wipro.customer.DTO.APIResponseDTO;
import com.wipro.customer.DTO.BankDTO;
import com.wipro.customer.DTO.CustomerDTO;
import com.wipro.customer.DTO.VendorDTO;
import com.wipro.customer.model.Customer;
import com.wipro.customer.repository.CustomerRepository;

import ch.qos.logback.core.util.Duration;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.util.List;
import java.util.Optional;

@Service
@RefreshScope
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private WebClient webClient;

	public Customer addCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	public CustomerDTO getCustomerById(int id) {
		Customer cust = customerRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Customer with ID " + id + " not found."));

		// Convert to DTO before returning
		return new CustomerDTO(cust.getCustomerId(), cust.getCustomerPhone(), cust.getCustomerBill(),
				cust.getCustomerBankId(), cust.getCustomerStore());
	}

	@CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "fallbackGetCustomerByBankId")
	@Retry(name = "${spring.application.name}", fallbackMethod = "fallbackGetCustomerByBankId")
	public APIResponseDTO getCustomerByBankId(String bankId) {
	    Customer cust = customerRepository.findByBankId(bankId)
	            .orElseThrow(() -> new ResourceNotFoundException("Customer with Account-ID " + bankId + " not found."));

	    WebClient webClient = WebClient.create();

	    // Fetch Bank details
	    Mono<BankDTO> bankDTOMono = webClient.get()
	            .uri("http://server-bank:6061/bank/{customerBankId}", cust.getCustomerBankId())
	            .retrieve()
	            .bodyToMono(BankDTO.class);

	    BankDTO bankDTO = bankDTOMono.block(); // Blocking call (can be optimized)

	    // Fetch Vendor details using vendor_name from BankDTO
	    Mono<VendorDTO> vendorDTOMono = webClient.get()
	            .uri("http://server-vendor:6062/vendor/{vendorAbbri}", bankDTO.getVendor_name()) // vendor_name is used as abbri
	            .retrieve()
	            .bodyToMono(VendorDTO.class);

	    VendorDTO vendorDTO = vendorDTOMono.block(); // Blocking call

	    // Convert Customer to DTO
	    CustomerDTO custDTO = mapper.map(cust, CustomerDTO.class);

	    // Populate APIResponseDTO
	    APIResponseDTO apiResponseDto = new APIResponseDTO();
	    apiResponseDto.setCustomerDTO(custDTO);
	    apiResponseDto.setBankDTO(bankDTO);
	    apiResponseDto.setVendorDTO(vendorDTO); // Include Vendor data

	    return apiResponseDto;
	}

	// Fallback method
	public APIResponseDTO fallbackGetCustomerByBankId(String bankId, Throwable throwable) {
	    BankDTO defaultBankDTO = new BankDTO();
	    defaultBankDTO.setAccountId(0);
	    defaultBankDTO.setHolder_name("Unknown");
	    defaultBankDTO.setHolder_phone("N/A");
	    defaultBankDTO.setBalance(0.0);
	    defaultBankDTO.setVendor_name("Unknown");

	    CustomerDTO defaultCustDTO = new CustomerDTO();

	    VendorDTO defaultVendorDTO = new VendorDTO();
	    defaultVendorDTO.setVendorId(0);
	    defaultVendorDTO.setVendorName("Unknown");
	    defaultVendorDTO.setVendorAbbri("N/A");

	    APIResponseDTO apiResponseDto = new APIResponseDTO();
	    apiResponseDto.setBankDTO(defaultBankDTO);
	    apiResponseDto.setCustomerDTO(defaultCustDTO);
	    apiResponseDto.setVendorDTO(defaultVendorDTO);

	    return apiResponseDto;
	}


	public List<Customer> getCustomersByBillGreaterThan(double amount) {
		return customerRepository.findCustomersByBillGreaterThan(amount);
	}

	public List<Customer> getCustomersByBillLessThan(double amount) {
		return customerRepository.findCustomersByBillLessThan(amount);
	}

	public Page<Customer> getCustomersPaged(int page, int size, String sortBy, String direction) {
		Sort sort = direction.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
		Pageable pageable = PageRequest.of(page, size, sort);
		return customerRepository.findAll(pageable);
	}

	public Customer updateCustomer(int id, Customer updatedCustomer) {
		Optional<Customer> existingCustomer = customerRepository.findById(id);
		if (existingCustomer.isPresent()) {
			Customer cust = existingCustomer.get();
			cust.setCustomerPhone(updatedCustomer.getCustomerPhone());
			cust.setCustomerBill(updatedCustomer.getCustomerBill());
			cust.setCustomerBankId(updatedCustomer.getCustomerBankId());
			cust.setCustomerStore(updatedCustomer.getCustomerStore());
			return customerRepository.save(cust);
		}
		throw new ResourceNotFoundException("Customer with ID " + id + " not found.");
	}

	public void deleteCustomer(int id) {
		if (!customerRepository.existsById(id)) {
			throw new ResourceNotFoundException("Customer with ID " + id + " not found.");
		}
		customerRepository.deleteById(id);
	}
}

/*
 * 
 * @CircuitBreaker(name="${spring.application.name}",fallbackMethod =
 * "getDefaultDepartment")
 * 
 * @Override public APIResponseDto getEmployeeById(Long employeeId) { // TODO
 * Auto-generated method stub Employee employee =
 * employeeRepository.findById(employeeId).get();
 * 
 * //ResponseEntity<DepartmentDto> responseEntity =
 * restTemplate.getForEntity("http://localhost:9090/departments/"+employee.
 * getDepartmentCode(), DepartmentDto.class);
 * 
 * 
 * //DepartmentDto departmentDto = responseEntity.getBody();
 * 
 * DepartmentDto departmentDto = webClient.get()
 * .uri("http://localhost:9090/departments/"+employee.getDepartmentCode())
 * .retrieve() .bodyToMono(DepartmentDto.class) .block();
 * 
 * 
 * //DepartmentDto departmentDto =
 * apiClient.getDepartmentByCode(employee.getDepartmentCode());
 * 
 * EmployeeDto employeeDto = mapper.map(employee, EmployeeDto.class);
 * 
 * APIResponseDto apiresponseDto = new APIResponseDto();
 * apiresponseDto.setDepartmentDto(departmentDto);
 * apiresponseDto.setEmployeeDto(employeeDto); return apiresponseDto;
 * 
 * }
 * 
 * 
 * public APIResponseDto getDefaultDepartment(Long employeeId, Exception
 * exception) { Employee employee =
 * employeeRepository.findById(employeeId).get();
 * 
 * DepartmentDto departmentDto = new DepartmentDto();
 * departmentDto.setDepartmentName("R&D Department");
 * departmentDto.setDepartmentCode("RD001");
 * departmentDto.setDepartmentDescription("Research and Development Department"
 * );
 * 
 * EmployeeDto employeeDto = mapper.map(employee, EmployeeDto.class);
 * APIResponseDto apiresponseDto = new APIResponseDto();
 * apiresponseDto.setDepartmentDto(departmentDto);
 * apiresponseDto.setEmployeeDto(employeeDto); return apiresponseDto;
 * 
 * }
 * 
 */
