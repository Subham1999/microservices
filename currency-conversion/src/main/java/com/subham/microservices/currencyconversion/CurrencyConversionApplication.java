package com.subham.microservices.currencyconversion;

import java.time.ZoneId;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CurrencyConversionApplication {

    public static void main(String[] args) {
	SpringApplication.run(CurrencyConversionApplication.class, args);
    }

    @PostConstruct
    public void init() {
	// Setting Spring Boot SetTimeZone
	TimeZone.setDefault(TimeZone.getTimeZone(ZoneId.of("UTC-7")));
    }
}
