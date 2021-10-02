package com.subham.restfulservices.profileservice.dao;

import java.time.LocalDateTime;
import java.util.Optional;

import com.subham.restfulservices.profileservice.dao.exception.EmailAlreadyExistsException;
import com.subham.restfulservices.profileservice.dao.exception.ProfileDoesNotExistException;
import com.subham.restfulservices.profileservice.dao.exception.UserNameAlreadyExistsException;
import com.subham.restfulservices.profileservice.model.Profile;
import com.subham.restfulservices.profileservice.model.util.Name;

/**
 * @author Subham Santra
 *
 */
public interface GenericProfileRespositoryService {
	// --------------------------------------------
	// RETRIEVE APIs
	// --------------------------------------------

	/**
	 * @return all profiles
	 */
	Iterable<Profile> findAll();

	/**
	 * @param email
	 * @return profile with given email
	 */
	Optional<Profile> findByUserName(String userName);

	/**
	 * @param email
	 * @return profile with given email
	 */
	Optional<Profile> findByEmail(String email);

	/**
	 * @param nickName
	 * @return collection of profiles with given nickName
	 */
	Iterable<Profile> findByNickName(String nickName);

	/**
	 * @param firstName
	 * @return collection of profiles with given firstName
	 */
	Iterable<Profile> findByFirstName(String firstName);

	/**
	 * @param lastName
	 * @return collection of profiles with given lastName
	 */
	Iterable<Profile> findByLastName(String lastName);

	/**
	 * @param dateOfJoin
	 * @return collection of profiles with given joining date
	 */
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
