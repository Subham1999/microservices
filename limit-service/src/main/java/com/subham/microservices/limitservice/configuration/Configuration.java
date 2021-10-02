package com.subham.microservices.limitservice.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties("limit-service")
@Component
public class Configuration {
	private int maximum;
	private int minimum;
}
