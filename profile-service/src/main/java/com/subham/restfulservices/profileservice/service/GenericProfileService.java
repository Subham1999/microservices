package com.subham.restfulservices.profileservice.service;

import java.time.LocalDateTime;
import java.util.Optional;

import com.subham.restfulservices.profileservice.dao.exception.EmailAlreadyExistsException;
import com.subham.restfulservices.profileservice.dao.exception.ProfileDoesNotExistException;
import com.subham.restfulservices.profileservice.dao.exception.UserNameAlreadyExistsException;
import com.subham.restfulservices.profileservice.model.Profile;
import com.subham.restfulservices.profileservice.model.util.Name;

public interface GenericProfileService {
	// --------------------------------------------
	// RETRIEVE APIs
	// --------------------------------------------

	Iterable<Profile> findAll();

	Optional<Profile> findByUserName(String userName);

	Optional<Profile> findByEmail(String email);

	Iterable<Profile> findByNickName(String nickName);

	Iterable<Profile> findByFirstName(String firstName);

	Iterable<Profile> findByLastName(String lastName);

	Iterable<Profile> findByDateOfJoin(LocalDateTime dateOfJoin);

	// --------------------------------------------
	// UPDATE APIs
	// --------------------------------------------

	boolean updateNameByUserName(String userName, Name name) throws ProfileDoesNotExistException;

	boolean updateEmailByUserName(String userName, String email)
			throws ProfileDoesNotExistException, EmailAlreadyExistsException;

	boolean updateImageByUserName(String userName, String imageUrl) throws ProfileDoesNotExistException;

	boolean updateBioByUserName(String userName, String bio) throws ProfileDoesNotExistException;

	boolean updateProfile(Profile profile) throws ProfileDoesNotExistException;

	// --------------------------------------------
	// CREATE APIs
	// --------------------------------------------

	boolean save(Profile profile) throws UserNameAlreadyExistsException, EmailAlreadyExistsException;

	// --------------------------------------------
	// DELETE APIs
	// --------------------------------------------

}
