package com.guitar.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.guitar.db.model.Location;

@Repository
public interface LocationJpaRepository extends JpaRepository<Location, Long> {

	Location findFirstByStateIgnoreCaseStartingWith(String stateName);

	/*
	 * List<Location> findByStateLike(String stateName);
	 * 
	 * List<Location> findByStateIsNull();
	 * 
	 * List<Location> findByStateIsNotNull();
	 * 
	 * List<Location> findByStateNotNull();
	 * 
	 * List<Location> findByStateNotLike(String stateName);
	 * 
	 * List<Location> findByStateIgnoreCase();
	 * 
	 * Location findByStateAndCountry(String stateName, String countryName);
	 * 
	 * List<Location> findByStateOrCountry(String stateName, String
	 * countryName);
	 * 
	 * List<Location> findByStateStartingWith(String stateName);
	 * 
	 * List<Location> findByStateIs(String stateName);
	 * 
	 * List<Location> findByState(String stateName);
	 * 
	 */
}
