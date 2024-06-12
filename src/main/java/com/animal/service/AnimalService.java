package com.animal.service;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
import com.animal.dao.AnimalDao;
import com.animal.dao.CategoryDao;
import com.animal.entities.Animal;
import com.animal.entities.AnimalDto;
import com.animal.entities.Category;

@Service
public class AnimalService {

	@Autowired
	AnimalDao dao;
	
	@Autowired
	private CategoryDao categoryDao;

	
	
	
	public String addAnimal(Animal animal)
	{
		dao.save(animal);
		return "Successfully save";
	}
	
	
	public Page<AnimalDto> getAllAnimals(String categoryName, String sortBy, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.asc(sortBy != null ? sortBy : "name")));
        Page<Animal> animals;

        if(categoryName != null && !categoryName.isEmpty()) {
            Category category = categoryDao.findByName(categoryName);
            animals = dao.findByCategory(category, pageable);
        }else{
            animals = dao.findAll(pageable);
            System.out.println("Service Animal List = "+animals.getContent());
        }

        return animals.map(this::convertToDto);
    }

	
	private AnimalDto convertToDto(Animal animal) {
		Long categoryId = (animal.getCategory() != null) ? animal.getCategory().getId() : null;
		Long lifeExpectancyId = (animal.getLifeExpectancy() != null) ? animal.getLifeExpectancy().getId() : null;
		String categoryName = (animal.getCategory() != null) ? animal.getCategory().getName() : "Unknown Category";
	    String lifeExpectancyName = (animal.getLifeExpectancy() != null) ? animal.getLifeExpectancy().getRange() : "Unknown Life Expectancy";

	    
        return new AnimalDto(
        		animal.getId(),
                animal.getName(),
                categoryId,
                categoryName,
                lifeExpectancyId,
                lifeExpectancyName,
                animal.getImage(),
                animal.getDescription()
        );
    }
	
	

	
	public AnimalDto getAnimalInfoById(Long id)
	{
		Animal a=dao.getAnimalById(id);
		System.out.println("Animal DTO= "+a);
		AnimalDto animalDto = convertToDto(a);
	    
	    return animalDto;
		
	}
		
	public String updateAnimal(Animal animal)
	{
		dao.save(animal);
		return "Updated Successfully";
	}
	
	//If an exception occurs during these operations, the transaction is rolled back, 
	//and no changes are persisted to the database.
	@Transactional
	public String deleteAnimalById(Long id)
	{
		dao.deleteById(id);
		return "Deleted Successfully";
	}


	
	public void save(Animal animal) 
	{
		dao.save(animal);
	}
	

	
	
}
