package com.subham.microservices.currencyconversion.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class CurrencyConversion {
	private String from;
	private String to;
	private BigDecimal quantity;
	private BigDecimal conversionCoefficient;
	private BigDecimal totalAmount;
	private String environment;
	public String getFrom() {
	    return from;
	}
	public void setFrom(String from) {
	    this.from = from;
	}
	public String getTo() {
	    return to;
	}
	public void setTo(String to) {
	    this.to = to;
	}
	public BigDecimal getQuantity() {
	    return quantity;
	}
	public void setQuantity(BigDecimal quantity) {
	    this.quantity = quantity;
	}
	public BigDecimal getConversionCoefficient() {
	    return conversionCoefficient;
	}
	public void setConversionCoefficient(BigDecimal conversionCoefficient) {
	    this.conversionCoefficient = conversionCoefficient;
	}
	public BigDecimal getTotalAmount() {
	    return totalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
	    this.totalAmount = totalAmount;
	}
	public String getEnvironment() {
	    return environment;
	}
	public void setEnvironment(String environment) {
	    this.environment = environment;
	}
	
	
}
