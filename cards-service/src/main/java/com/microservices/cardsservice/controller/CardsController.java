package com.microservices.cardsservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.microservices.cardsservice.config.CardsServiceConfig;
import com.microservices.cardsservice.entity.Cards;
import com.microservices.cardsservice.entity.Customer;
import com.microservices.cardsservice.repository.CardsRepository;
import com.microservices.cardsservice.entity.Properties;

@RestController
public class CardsController {
	
	@Autowired
	private CardsRepository cardsRepository;
	
	@Autowired
	private CardsServiceConfig cardsConfig;
	
	@PostMapping("/myCards")
	public List<Cards> getCardsDetails(@RequestBody Customer customer) {
		
		List<Cards> cards = cardsRepository.findByCustomerId(customer.getCustomerId());
		if(cards != null) {
			return cards;
		} else {
			return null;
		}
	}
	
	@GetMapping("cards/properties")
	public String getPropertyDetails() throws JsonProcessingException {
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		Properties properties = new Properties(cardsConfig.getMsg(), cardsConfig.getBuildVersion(), 
				cardsConfig.getMailDetails(), cardsConfig.getActiveBranches());
		String jsonString = ow.writeValueAsString(properties);
		return jsonString;
	}

}
