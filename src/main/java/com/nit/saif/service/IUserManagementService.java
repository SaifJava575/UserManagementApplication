package com.nit.saif.service;

import java.util.Map;

import com.nit.saif.bindings.LoginForm;
import com.nit.saif.bindings.UnlockAccountForm;
import com.nit.saif.bindings.UserRegistrationForm;

public interface IUserManagementService {
	
	String signin(LoginForm loginForm);

	String checkEmail(String email);

	Map<Integer, String> getCountries();

	Map<Integer, String> getStates(Integer countryId);

	Map<Integer, String> getCities(Integer stateId);

	String signup(UserRegistrationForm userRegForm);

	String unlock(UnlockAccountForm unlockAccFrom);

	String forgotPwd(String email);
}
