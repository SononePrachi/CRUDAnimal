package com.animal.dao;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.animal.entities.Animal;

public interface AnimalDao extends JpaRepository<Animal, Integer>,PagingAndSortingRepository<Animal,Integer>{

	Animal getAnimalById(Long id);

	void deleteById(Long id);

	Page<Animal> findByCategoryOrderByCategory(String category, Pageable pageable);

    Page<Animal> findAllByOrderByNameAsc(Pageable pageable);

    @Query("SELECT a FROM Animal a " +
	           "ORDER BY " +
	           "CONVERT(SUBSTRING_INDEX(a.lifeExpectancy, '-', 1), SIGNED), " + // Sort by minimum range value
	           "CONVERT(SUBSTRING_INDEX(a.lifeExpectancy, '-', -1), SIGNED)")
    Page<Animal> findAllByOrderByLifeExpectancyAsc(Pageable pageable);
	
	
}
