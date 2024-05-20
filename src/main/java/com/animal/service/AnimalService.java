package com.animal.service;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.animal.dao.AnimalDao;
import com.animal.entities.Animal;

@Service
public class AnimalService {

	@Autowired
	AnimalDao dao;
	
	public String addAnimal(Animal animal)
	{
		dao.save(animal);
		return "Successfully save";
	}
	
	
	public Page<Animal> getAllAnimals(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return dao.findAll(pageable);
    }
	
	
	public Animal getAnimalInfoById(Long id)
	{
		Animal a=dao.getAnimalById(id);
		return a;
	}
		
	public String updateAnimal(Animal animal)
	{
		dao.save(animal);
		return "Updated Successfully";
	}
	
	@Transactional
	public String deleteAnimalById(Long id)
	{
		dao.deleteById(id);
		return "Deleted Successfully";
	}

	public Page<Animal> getAnimalsSortedByCategory(String category, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return dao.findByCategoryOrderByCategory(category, pageable);
    }

    public Page<Animal> getAnimalsSortedAlphabetically(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return dao.findAllByOrderByNameAsc(pageable);
    }

    public Page<Animal> getAnimalsSortedLifeExpectency(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return dao.findAllByOrderByLifeExpectancyAsc(pageable);
    }
	
	
}
