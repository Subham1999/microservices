package com.subham.microservices.limitservice.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.subham.microservices.limitservice.configuration.Configuration;
import com.subham.microservices.limitservice.model.Limits;

@RestController
public class LimitServiceRestController {
	@Autowired
	private Environment environment;
	@Autowired
	private Configuration configuration;

	@GetMapping("limit-service/limits")
	public Map<String, Object> getLimits() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("limits", new Limits(configuration.getMinimum(), configuration.getMaximum()));
		map.put("environment", environment.getProperty("local.server.port"));
		return map;
	}
}
