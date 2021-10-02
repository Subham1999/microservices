package com.subham.microservices.currencyexchange.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.subham.microservices.currencyexchange.model.CurrencyExchange;
import com.subham.microservices.currencyexchange.model.CurrencyExchangeRegistry;

@RestController
public class ExchangeController {

	@Autowired
	private Environment environment;

	@GetMapping("/currency-exchange/{from}/to/{to}")
	public CurrencyExchange getCurrencyExchange(@PathVariable String from, @PathVariable String to) {
		CurrencyExchange currencyExchange = CurrencyExchangeRegistry.getCurrencyExchange(from, to);
		String serverPort = environment.getProperty("local.server.port");
		currencyExchange.setEnvironment("server.port = " + serverPort);
		return currencyExchange;
	}
}
