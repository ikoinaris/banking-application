package com.microservices.accountsservice.controller;

import com.microservices.accountsservice.entity.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.accountsservice.config.AccountsServiceConfig;
import com.microservices.accountsservice.entity.Accounts;
import com.microservices.accountsservice.entity.Customer;
import com.microservices.accountsservice.repository.AccountsRepository;

import com.fasterxml.jackson.databind.ObjectWriter;

@RestController
public class AccountsController {
	
	@Autowired
	private AccountsRepository accountsRepository;
	
	@Autowired
	private AccountsServiceConfig accountsConfig;
	
	@PostMapping("/myAccount")
	public Accounts getAccountDetails(@RequestBody Customer customer) {
		
		Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId());
		if (accounts != null) {
			return accounts;
		} else {
			return null;
		}		
	}
	
	@GetMapping("/account/properties")
	public String getPropertyDetails() throws JsonProcessingException {
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		Properties properties = new Properties(accountsConfig.getMsg(), accountsConfig.getBuildVersion(), 
				accountsConfig.getMailDetails(), accountsConfig.getActiveBranches());
		String jsonString = ow.writeValueAsString(properties);
		return jsonString;
	}

}
