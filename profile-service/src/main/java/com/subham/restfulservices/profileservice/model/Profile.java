package com.subham.restfulservices.profileservice.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Profile {
	private String userName;
	private String firstName;
	private String lastName;
	private String nickName;
	private LocalDate dateOfBirth;
	private LocalDate dateOfJoin;
	private String email;
	private String password;
	private String imageUrl;
	private LocalDate imageUrlLastUpdate;
	private String bio;
	private LocalDate bioLastUpdate;
}
