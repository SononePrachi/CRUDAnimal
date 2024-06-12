package com.animal.dao;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.animal.entities.Animal;
import com.animal.entities.AnimalDto;
import com.animal.entities.Category;

public interface AnimalDao extends JpaRepository<Animal, Integer>,PagingAndSortingRepository<Animal,Integer>{

	Animal getAnimalById(Long id);

	void deleteById(Long id);

	Page<Animal> findByCategory(Category category, Pageable pageable);
	
//	Page<Animal> findByCategoryOrderByCategory(Category category, Pageable pageable);

    Page<AnimalDto> findAllByOrderByNameAsc(Pageable pageable);
    

    @Query("SELECT a FROM Animal a " +
	           "ORDER BY " +
	           "CONVERT(SUBSTRING_INDEX(a.lifeExpectancy.range, '-', 1), SIGNED), " + // Sort by minimum range value
	           "CONVERT(SUBSTRING_INDEX(a.lifeExpectancy.range, '-', -1), SIGNED)")
    Page<AnimalDto> findAllByOrderByLifeExpectancyAsc(Pageable pageable);
	
	
}
