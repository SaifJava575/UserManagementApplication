package com.nit.saif.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nit.saif.bindings.LoginForm;
import com.nit.saif.service.IUserManagementService;

@RestController
public class LoginRestController {
	@Autowired
	private IUserManagementService userMgmtService;
	
	@PostMapping("/login")
	public String login(@RequestBody LoginForm loginForm) {		
		String msg = userMgmtService.signin(loginForm);	
		return msg;
	}
}
