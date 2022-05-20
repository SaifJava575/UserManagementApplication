package com.nit.saif.bindings;

import lombok.Data;

@Data
public class UnlockAccountForm {
	
	private String email;
	private String tmpPwd;
	private String newPwd;
	private String confirmNewPwd;
}
