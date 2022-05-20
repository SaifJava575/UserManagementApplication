package com.nit.saif.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nit.saif.bindings.UnlockAccountForm;
import com.nit.saif.service.IUserManagementService;

@RestController
public class UnlockAccRestController {
	@Autowired
	private IUserManagementService userMgmtService;
	
	@PostMapping("/unlock")
	public String unlock(@RequestBody UnlockAccountForm unlockAccForm) {	
		String unlockMsg = userMgmtService.unlock(unlockAccForm);	
		return unlockMsg;
	}
	
	
}
