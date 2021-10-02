package com.subham.microservices.currencyexchange.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyExchange {

	private String from;
	private String to;
	private BigDecimal conversionCoefficient;
	private String environment;
}
