package com.nit.saif.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.nit.saif.service.IUserManagementService;

@RestController
public class ForgotPwdRestController {
	@Autowired	
	private IUserManagementService userMgmtService;

	@GetMapping("/forgotpwd/{email}")
	public String forgotPwd(@PathVariable("email") String email) {		
		String forgotPwdMsg = userMgmtService.forgotPwd(email);		
		return forgotPwdMsg;
	}

}
