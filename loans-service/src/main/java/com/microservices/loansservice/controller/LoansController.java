package com.microservices.loansservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.microservices.loansservice.entity.Properties;
import com.microservices.loansservice.config.LoansServiceConfig;
import com.microservices.loansservice.entity.Customer;
import com.microservices.loansservice.entity.Loans;
import com.microservices.loansservice.repository.LoansRepository;


@RestController
public class LoansController {
	
	@Autowired
	private LoansRepository loansRepository;
	
	@Autowired
	private LoansServiceConfig loansConfig;
	
	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(LoansController.class);
	
	@PostMapping("/myLoans")
	public List<Loans> getLoansDetails(@RequestBody Customer customer) {
		
		logger.info("getLoansDetails() method started");
		List<Loans> loans = loansRepository.findByCustomerIdOrderByStartDtDesc(customer.getCustomerId());
		logger.info("getLoansDetails() method ended");
		if(loans != null) {
			return loans;
		} else {
			return null;
		}
		
	}
	
	@GetMapping("loans/properties")
	public String getPropertyDetails() throws JsonProcessingException {
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		com.microservices.loansservice.entity.Properties properties = new Properties(loansConfig.getMsg(), loansConfig.getBuildVersion(), 
				loansConfig.getMailDetails(), loansConfig.getActiveBranches());
		String jsonString = ow.writeValueAsString(properties);
		return jsonString;
	} 

}
