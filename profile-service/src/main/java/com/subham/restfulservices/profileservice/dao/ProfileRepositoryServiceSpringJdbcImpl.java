package com.subham.restfulservices.profileservice.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.subham.restfulservices.profileservice.dao.exception.EmailAlreadyExistsException;
import com.subham.restfulservices.profileservice.dao.exception.ProfileDoesNotExistException;
import com.subham.restfulservices.profileservice.dao.exception.UserNameAlreadyExistsException;
import com.subham.restfulservices.profileservice.model.Profile;
import com.subham.restfulservices.profileservice.model.util.Name;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Subham Santra
 *
 */
@Slf4j
@Repository
public class ProfileRepositoryServiceSpringJdbcImpl implements GenericProfileRespositoryService {

	private static final int[] ARG_TYPES = new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
			Types.DATE, Types.DATE, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.DATE, Types.VARCHAR,
			Types.DATE };

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private static final RowMapper<Profile> PROFILE_ROW_MAPPER = new RowMapper<Profile>() {
		@Override
		public Profile mapRow(ResultSet rs, int rowNum) throws SQLException {
			Profile profile = Profile.builder().userName(rs.getString("user_name"))
					.firstName(rs.getString("first_name")).lastName(rs.getString("last_name"))
					.nickName(rs.getString("nick_name"))
					.dateOfBirth(LocalDate.parse(rs.getDate("date_of_birth").toString(), DATE_TIME_FORMATTER))
					.dateOfJoin(LocalDate.parse(rs.getDate("date_of_join").toString(), DATE_TIME_FORMATTER))
					.email(rs.getString("email")).password(rs.getString("password")).imageUrl(rs.getString("image_url"))
					.imageUrlLastUpdate(
							LocalDate.parse(rs.getDate("image_url_last_update").toString(), DATE_TIME_FORMATTER))
					.bio(rs.getString("bio"))
					.bioLastUpdate(LocalDate.parse(rs.getDate("bio_last_update").toString(), DATE_TIME_FORMATTER))
					.build();
			return profile;
		}
	};

	@Override
	public Iterable<Profile> findAll() {
		log.debug("find all");
		List<Profile> list = jdbcTemplate.query("select * from profile", PROFILE_ROW_MAPPER);
		return list;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Optional<Profile> findByUserName(String userName) {
		log.debug("find by username");
		Profile profile = jdbcTemplate.queryForObject("SELECT * FROM profile WHERE user_name = ?",
				new Object[] { userName }, Profile.class);
		return Optional.ofNullable(profile);
	}

	@SuppressWarnings("deprecation")
	@Override
	public Optional<Profile> findByEmail(String email) {
		log.debug("find by email");
		Profile profile = jdbcTemplate.queryForObject("SELECT * FROM profile WHERE email = ?", new Object[] { email },
				Profile.class);
		return Optional.ofNullable(profile);
	}

	@SuppressWarnings("deprecation")
	@Override
	public Iterable<Profile> findByNickName(String nickName) {
		log.debug("find by nickname");
		List<Profile> list = jdbcTemplate.query("select * from profile WHERE nick_name = ?", new Object[] { nickName },
				PROFILE_ROW_MAPPER);
		return list;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Iterable<Profile> findByFirstName(String firstName) {
		List<Profile> list = jdbcTemplate.query("select * from profile WHERE first_name = ?",
				new Object[] { firstName }, PROFILE_ROW_MAPPER);
		return list;
	}

	@SuppressWarnings("deprecation")
	@Override
	public Iterable<Profile> findByLastName(String lastName) {
		List<Profile> list = jdbcTemplate.query("select * from profile WHERE last_name = ?", new Object[] { lastName },
				PROFILE_ROW_MAPPER);
		return list;
	}

	@Override
	public Iterable<Profile> findByDateOfJoin(LocalDateTime dateOfJoin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateNameByUserName(String userName, Name name) throws ProfileDoesNotExistException {
		log.debug("updating name by userName -> name = {} and userName = {}", name, userName);
		if (userName == null || name == null) {
			log.debug("updation falied");
			return false;
		} else if (!name.getFirstName().isBlank() && !name.getLastName().isBlank()) {
			int numOfRows = jdbcTemplate.update("UPDATE profile SET first_name = ?, last_name = ? WHERE user_name = ?",
					new Object[] { name.getFirstName(), name.getLastName(), userName },
					new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR });

			if (numOfRows == 1) {
				log.debug("updated name by userName");
				return true;
			} else {
				log.debug("updation falied");
				throw new ProfileDoesNotExistException();
			}
		} else if (!name.getFirstName().isBlank()) {
			int numOfRows = jdbcTemplate.update("UPDATE profile SET first_name = ? WHERE user_name = ?",
					name.getFirstName(), userName);
			if (numOfRows == 1) {
				return true;
			} else {
				log.debug("updation falied");
				throw new ProfileDoesNotExistException();
			}
		} else if (!name.getLastName().isBlank()) {
			int numOfRows = jdbcTemplate.update("UPDATE profile SET last_name = ? WHERE user_name = ?",
					name.getLastName(), userName);
			if (numOfRows == 1) {
				log.debug("updated only lastName by userName");
				return true;
			} else {
				log.debug("updation falied");
				throw new ProfileDoesNotExistException();
			}
		} else {
			log.debug("updation falied");
			return false;
		}
	}

	@Override
	public boolean updateEmailByUserName(String userName, String email)
			throws ProfileDoesNotExistException, EmailAlreadyExistsException {
		log.debug("updating email by userName -> email={}, userName={}", email, userName);
		int numOfRows = jdbcTemplate.update("UPDATE profile SET email = ? WHERE user_name = ?", email, userName);
		boolean success = numOfRows == 1;
		if (success) {
			log.debug("updated email by userName");
		} else {
			log.debug("updation failed");
		}
		return success;
	}

	@Override
	public boolean updateImageByUserName(String userName, String imageUrl) throws ProfileDoesNotExistException {
		int numOfRows = jdbcTemplate.update(
				"UPDATE profile SET image_url = ?, image_url_last_update = ? WHERE user_name = ?", imageUrl,
				LocalDate.now(), userName);
		return numOfRows == 1;
	}

	@Override
	public boolean updateBioByUserName(String userName, String bio) throws ProfileDoesNotExistException {
		int numOfRows = jdbcTemplate.update("UPDATE profile SET bio = ?, bio_last_update = ? WHERE user_name = ?", bio,
				LocalDate.now(), userName);
		return numOfRows == 1;
	}

	@Override
	public boolean updateProfile(Profile profile) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean save(Profile profile) throws UserNameAlreadyExistsException, EmailAlreadyExistsException {
		log.debug("saving new profile");
		boolean saved = save(profile, true);
		if (saved) {
			log.debug("saved");
		} else {
			log.debug("not saved");
		}
		return saved;
	}

	private boolean save(Profile profile, boolean isNewEntry)
			throws UserNameAlreadyExistsException, EmailAlreadyExistsException {

		if (isNewEntry) {
			LocalDate now = LocalDate.now();
			profile.setDateOfJoin(now);
			if (profile.getBio() != null) {
				profile.setBioLastUpdate(now);
			}
			if (profile.getImageUrl() != null) {
				profile.setImageUrlLastUpdate(now);
			}
		}
		try {
			int numOfRows = jdbcTemplate.update("INSERT INTO profile VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
					ObjectArray(profile), ARG_TYPES);
			if (numOfRows == 1) {
				log.debug("successfully saved profile");
				return true;
			}
		} catch (Exception e) {
			log.debug("profile couldnot be saved");
			log.error(e.getMessage());
		}
		
		log.debug("profile couldnot be saved");
		return false;
	}

	private final Object[] ObjectArray(Profile profile) {
		return new Object[] { profile.getUserName(), profile.getFirstName(), profile.getLastName(),
				profile.getNickName(), profile.getDateOfBirth(), profile.getDateOfJoin(), profile.getEmail(),
				profile.getPassword(), profile.getImageUrl(), profile.getImageUrlLastUpdate(), profile.getBio(),
				profile.getBioLastUpdate() };
	}

}
