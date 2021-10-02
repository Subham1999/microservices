package com.subham.microservices.currencyexchange.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CurrencyExchangeRegistry {
	private static Map<List<String>, BigDecimal> map = new HashMap<List<String>, BigDecimal>();

	static {
		map.put(List.of("INR", "USD"), BigDecimal.valueOf(75));
		map.put(List.of("USD", "IND"), BigDecimal.valueOf(1 / 75.0));
	}

	public static CurrencyExchange getCurrencyExchange(String from, String to) {
		BigDecimal conversionCoefficient = map.get(List.of(from, to));
		System.err.println(conversionCoefficient);
		CurrencyExchange currencyExchange = new CurrencyExchange(from, to, conversionCoefficient, null);
		return currencyExchange;
	}
}
