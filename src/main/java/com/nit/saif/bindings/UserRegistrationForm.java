package com.nit.saif.bindings;

import java.time.LocalDate;

import lombok.Data;
@Data
public class UserRegistrationForm {
	
	private String firstName;
	private String lastName;
	private String userEmail;
	private long phno;
	private LocalDate dob;
	private String gender;
	private Integer countryId;
	private Integer stateId;
	private Integer cityId;
}
