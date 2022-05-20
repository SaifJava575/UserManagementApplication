package com.nit.saif.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nit.saif.entity.City;

public interface ICityRepository extends JpaRepository<City,Integer> {
	
	public List<City> findByStateId(Integer stateId);
}
