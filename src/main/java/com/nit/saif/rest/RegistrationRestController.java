package com.nit.saif.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nit.saif.bindings.UserRegistrationForm;
import com.nit.saif.service.IUserManagementService;

@RestController
public class RegistrationRestController {
	@Autowired
	private IUserManagementService userMgmtService;

	@GetMapping("/email/{emailId}")
	public String checkEmail(@PathVariable("emailId") String email) {	
		String msg = userMgmtService.checkEmail(email);		
		return msg;
	}//checkEmail()
	
	@GetMapping("/country")
	public Map<Integer,String> getCountries() {		
		Map<Integer, String> countries = userMgmtService.getCountries();	
		return countries;
	}
	
	@GetMapping("/states/{countryId}")
	public Map<Integer,String> getstates(@PathVariable("countryId") Integer countryId){	
		Map<Integer, String> states = userMgmtService.getStates(countryId);		
		return states;
	}
	
	@GetMapping("cities/{stateId}")
	public Map<Integer,String> getCities(@PathVariable("stateId") Integer stateId){	
		Map<Integer, String> cities = userMgmtService.getCities(stateId);		
		return cities;
	}
	
	@PostMapping("/signup")
	public String singup(@RequestBody UserRegistrationForm userRegForm) {		
		String msg = userMgmtService.signup(userRegForm);		
		return msg;
	}
}
