package com.animal.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	public List<Animal> getAllAnimals()
	{
		List<Animal> l=dao.findAll();
		return l;
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
	
	
	public List<Animal> getAnimalsSortedByCategory(String category) {
		return dao.getAnimalsSortedByCategory(category);
	}

	public List<Animal> getAnimalsSortedAlphabetically() {
		return dao.findAllByOrderByName();
	}
	
	public List<Animal> getAnimalsSortedLifeExpectency() {
		return dao.findAllOrderByLifeExpectancyRange();
	}

	
	
	
}
