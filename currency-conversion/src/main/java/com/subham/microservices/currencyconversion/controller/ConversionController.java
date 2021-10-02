package com.subham.microservices.currencyconversion.controller;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.subham.microservices.currencyconversion.model.CurrencyConversion;

@RestController
public class ConversionController {

	@Autowired
	private Environment environment;

	@GetMapping("/currency-conversion/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion getCurrencyConversion(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<CurrencyConversion> responseEntity = restTemplate.getForEntity(
				"http://localhost:8000/currency-exchange/{from}/to/{to}", CurrencyConversion.class,
				Map.of("from", from, "to", to));

		CurrencyConversion currencyConversion = responseEntity.getBody();
		currencyConversion.setQuantity(quantity);
		try {
			currencyConversion.setTotalAmount(
				currencyConversion.getConversionCoefficient().multiply(currencyConversion.getQuantity()));
		} catch (Exception e) {
			throw new RuntimeException("Something is not right");
		}
		currencyConversion.setEnvironment("server.port = " + environment.getProperty("local.server.port"));
		return currencyConversion;
	}
}
