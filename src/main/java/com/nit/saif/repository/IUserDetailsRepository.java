package com.nit.saif.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nit.saif.entity.UserDtlsEntity;

public interface IUserDetailsRepository extends JpaRepository<UserDtlsEntity,Integer> {
	
	//@Query("from UserDtlsEntity where userEmail=:empName")
		UserDtlsEntity findByUserEmailAndUserPwd(String userEmail, String userPwd);
		
		UserDtlsEntity findByUserEmail(String userEmail);
}
