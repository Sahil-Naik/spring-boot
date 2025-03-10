package com.wipro.customer.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class MessageRestController {
	 
	@Value("Hello from main App")
	private String message;
	
	@GetMapping("/message")
	public String message()
	{
		return message;
	}
	
}

/*
 * class MessageRestController {
 * 
 * @Value("${message:Hello this is from Bank}") private String message;
 * 
 * @GetMapping("/message") String getMessage() { return this.message; } }
 */
