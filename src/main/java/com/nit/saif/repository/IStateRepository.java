package com.nit.saif.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nit.saif.entity.State;

public interface IStateRepository extends JpaRepository<State,Integer> {
	
	public List<State> findByCountryId(Integer countryId);
}
