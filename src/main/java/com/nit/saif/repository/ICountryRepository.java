package com.nit.saif.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nit.saif.entity.Country;

public interface ICountryRepository extends JpaRepository<Country,Integer> {

}
