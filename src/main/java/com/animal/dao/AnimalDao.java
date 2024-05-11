package com.animal.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.animal.entities.Animal;

public interface AnimalDao extends JpaRepository<Animal, Integer>{

	Animal getAnimalById(Long id);

	void deleteById(Long id);

	List<Animal> getAnimalsSortedByCategory(String category);

	List<Animal> findAllByOrderByName();

	@Query("SELECT a FROM Animal a " +
	           "ORDER BY " +
	           "CONVERT(SUBSTRING_INDEX(a.lifeExpectancy, '-', 1), SIGNED), " + // Sort by minimum range value
	           "CONVERT(SUBSTRING_INDEX(a.lifeExpectancy, '-', -1), SIGNED)")
	List<Animal> findAllOrderByLifeExpectancyRange();
	
	
	
}
