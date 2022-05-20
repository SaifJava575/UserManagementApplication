package com.nit.saif.service.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nit.saif.bindings.LoginForm;
import com.nit.saif.bindings.UnlockAccountForm;
import com.nit.saif.bindings.UserRegistrationForm;
import com.nit.saif.entity.City;
import com.nit.saif.entity.Country;
import com.nit.saif.entity.State;
import com.nit.saif.entity.UserDtlsEntity;
import com.nit.saif.repository.ICityRepository;
import com.nit.saif.repository.ICountryRepository;
import com.nit.saif.repository.IStateRepository;
import com.nit.saif.repository.IUserDetailsRepository;
import com.nit.saif.service.IUserManagementService;
import com.nit.saif.utility.EmailUtility;
import com.nit.saif.utility.RandomPasswordGenerator;

@Service
public class UserManagementServiceImpl implements IUserManagementService {
	@Autowired
	private IUserDetailsRepository userRepo;
	@Autowired
	private ICountryRepository countryRepo;
	@Autowired
	private IStateRepository stateRepo;
	@Autowired
	private ICityRepository cityRepo;
	@Autowired
	private EmailUtility emailUtility;
	
	@Override
	public String signin(LoginForm loginForm) {
		UserDtlsEntity user = userRepo.findByUserEmailAndUserPwd(loginForm.getEmail(), loginForm.getPwd());
		if (user == null) {
			return "Invalid Credentials";
		}
		if (user.getAccStatus().equals("locked")) {
			return "Your Account is locked";
		}
		return "SUCCESS";
	}

	@Override
	public String checkEmail(String email) {
		UserDtlsEntity user = userRepo.findByUserEmail(email);
		if (user != null) {
			return "Duplicate";
		}
		return "Unique";
	}

	@Override
	public Map<Integer, String> getCountries() {
		Map<Integer, String> countryMap = new HashMap<>();
		List<Country> countriesList = countryRepo.findAll();
		for (Country country : countriesList) {
			countryMap.put(country.getCountryId(), country.getCountryName());
		}
		return countryMap;
	}

	@Override
	public Map<Integer, String> getStates(Integer countryId) {
		Map<Integer, String> stateMap = new HashMap<>();
		List<State> stateList = stateRepo.findByCountryId(countryId);
		for (State state : stateList) {
		stateMap.put(state.getStateId(), state.getStateName());
		}
		return stateMap;
	}

	@Override
	public Map<Integer, String> getCities(Integer stateId) {
		Map<Integer, String> cityMap = new HashMap<>();
		List<City> cityList = cityRepo.findByStateId(stateId);
		for (City city : cityList) {
			cityMap.put(city.getCityId(), city.getCityName());
		}
		return cityMap;
	}

	@Override
	public String signup(UserRegistrationForm userRegForm) {
		UserDtlsEntity userDtlsEntity = new UserDtlsEntity();
		String tempPwd = RandomPasswordGenerator.getAlphaNumericString();
		BeanUtils.copyProperties(userRegForm, userDtlsEntity);
		userDtlsEntity.setUserPwd(tempPwd);
		userDtlsEntity.setAccStatus("locked");

		boolean isSent = emailUtility.sendEmail(userDtlsEntity.getUserEmail(), "Registration success",
				emailBody("EmailTemplateForRegistration.txt", userDtlsEntity));

		if (isSent == false) {
			return "failed";
		}

//		EmailService emailService = new EmailService();
//		emailService.createEmail(userRegForm.getEmail(), "Registarion Success",
//				EmailBody.emailBody(userRegForm.getFirstName(), userRegForm.getLastName(), tempPwd));
		
		userRepo.save(userDtlsEntity);
		return "Please check your email to unlock account";
	}

	@Override
	public String unlock(UnlockAccountForm unlockAccFrom) {
		
		if (!unlockAccFrom.getNewPwd().equals(unlockAccFrom.getConfirmNewPwd())) {
			return "New password and confirm password not matched";
		}
		UserDtlsEntity user = userRepo.findByUserEmailAndUserPwd(unlockAccFrom.getEmail(),
				unlockAccFrom.getTmpPwd());
		if (user == null) {
			return "Temporary password not matched";
		}
		user.setUserPwd(unlockAccFrom.getConfirmNewPwd());
		user.setAccStatus("Active");
		userRepo.save(user);
		return "Account unlocked, please proceed with login";
	}

	@Override
	public String forgotPwd(String email) {
		UserDtlsEntity user = userRepo.findByUserEmail(email);
		if (user == null) {
			return "Invalid Email";
		}
		boolean isSent = emailUtility.sendEmail(email, "AshokIt forgot password",
				emailBody("EmailTemplateForForgotPassword.txt", user));
		if (isSent == false) {
			return "failed";
		}
//		EmailService emailService = new EmailService();
//		emailService.createEmail(email, "Forgot Password", "This is your Current password : " + user.getUserPwd());
		return "please check you email password sent";
	}
	
	private String emailBody(String file, UserDtlsEntity user) {
		StringBuffer buffer = new StringBuffer();
		FileReader fileReader = null;
		BufferedReader buffredReader = null;
		String body = null;
		try {
			fileReader = new FileReader(file);
			buffredReader = new BufferedReader(fileReader);
			String line = buffredReader.readLine();
			while (line != null) {
				buffer.append(line);
				line = buffredReader.readLine();
			}
			body = buffer.toString();
			body = body.replace("{FNAME}", user.getFirstName());
			body = body.replace("{LNAME}", user.getLastName());
			body = body.replace("{TEMP-PWD}", user.getUserPwd());
			body = body.replace("{EMAIL}", user.getUserEmail());
			body = body.replace("{PWD}", user.getUserPwd());			
		} // try
		catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} // catch
		finally {
			try {
				if (fileReader != null) {
					fileReader.close();
				}
			} // try
			catch (IOException io) {
				io.printStackTrace();
			} // catch
		} // finally
		return body;
	}// emailBody()


}
