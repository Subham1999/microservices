package com.subham.restfulservices.profileservice.controller;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.subham.restfulservices.profileservice.dao.ProfileRepositoryServiceSpringJdbcImpl;
import com.subham.restfulservices.profileservice.dao.exception.EmailAlreadyExistsException;
import com.subham.restfulservices.profileservice.dao.exception.ProfileDoesNotExistException;
import com.subham.restfulservices.profileservice.dao.exception.UserNameAlreadyExistsException;
import com.subham.restfulservices.profileservice.model.Profile;
import com.subham.restfulservices.profileservice.model.util.Name;

@RestController
public class ProfileController {
	@Autowired
	private ProfileRepositoryServiceSpringJdbcImpl profileRepository;

	@GetMapping("/all")
	public Iterable<Profile> getList() {
		return profileRepository.findAll();
	}

	@GetMapping("/get-client-info")
	public void testInfo(HttpServletRequest httpServletRequest) {
		Enumeration<String> headerNames = httpServletRequest.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement();
			System.err
					.println("Header name : " + headerName + "\t\tvalue = " + httpServletRequest.getHeader(headerName));
		}
	}

	@GetMapping("/test/change/name/{un}/{fn},{ln}")
	public String updatepProfileName(@PathVariable String un, @PathVariable String fn, @PathVariable String ln) {
		Name name = new Name();
		name.setFirstName(fn);
		name.setLastName(ln);
		try {
			profileRepository.updateNameByUserName(un, name);
			return "Done!";
		} catch (ProfileDoesNotExistException e) {
			e.printStackTrace();
			return "Not done!";
		}
	}

	@PostMapping("/test/save/profile")
	public String saveProfile(@RequestBody Profile profile)
			throws UserNameAlreadyExistsException, EmailAlreadyExistsException {
		boolean saved = profileRepository.save(profile);
		if (saved) {
			return "Done";
		} else {
			return "Not done";
		}
	}
}
