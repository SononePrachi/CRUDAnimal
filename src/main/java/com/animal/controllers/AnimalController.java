package com.animal.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.animal.entities.Animal;
import com.animal.service.AnimalService;
import com.animal.service.FileStorageService;


@Controller
public class AnimalController {
	
	
	@Autowired
    private FileStorageService fileStorageService;
	
	@Autowired
	private AnimalService service;
	
	//to getting the home page with all animal's list
	@GetMapping("/animal/home")
    public String homePage(@RequestParam(defaultValue = "0") int page, 
                           @RequestParam(defaultValue = "5") int size, 
                           Model m) {
        Page<Animal> animalPage = service.getAllAnimals(page, size);
        List<Animal> animalList = animalPage.getContent();

        m.addAttribute("allAnimalsList", animalList);
        m.addAttribute("currentPage", page);
        m.addAttribute("totalPages", animalPage.getTotalPages());
        m.addAttribute("totalItems", animalPage.getTotalElements());

        return "animal";
    }
	
	
	
	//File object of Multipartfile Type is used to get the uploaded file.
	//@RequestParam annotation is used to bind request parameters from the HTTP request to method parameters in your controller methods
	//to add animal
	@PostMapping("/animal/addAnimal")
	public String addAnimal(@RequestParam("image") MultipartFile file,
			@RequestParam("name") String name,
			@RequestParam("category") String category,
			@RequestParam("description") String description,
			@RequestParam("lifeExpectancy") String lifeExpectancy,
			RedirectAttributes redirectAttributes)//is use to redirect success or error message
	{
		    
		System.out.println("File = "+file);
		try {
			String storedFileName = fileStorageService.storeFile(file);//to store uploaded file in our folder
			//for storing all fields in a animal object
			Animal animal = new Animal();
            
			animal.setName(name);
            animal.setCategory(category);
            animal.setDescription(description);
            animal.setLifeExpectancy(lifeExpectancy);
            animal.setImage(storedFileName);
			System.out.println("animal is = "+animal);
		    
			service.addAnimal(animal);
		    
		    //When you want to pass data to another request and ensure that the data is available only once (i.e., during the redirect)
		    redirectAttributes.addFlashAttribute("successMessage", "Animal added Successfully");
		
	   }catch(Exception e) {
	    	System.out.println("Exception is = "+e);
	    	redirectAttributes.addFlashAttribute("errorMessage", "Something went wrong");
	 }
		
		return "redirect:/animal/home";	
	}
	
	 //to show edit page for update specific animal
	 @RequestMapping("/animal/edit/{id}")
	public String editAnimalShow(@PathVariable Long id, Model model)
	{	
		System.out.println("id="+id);
		//to get specific id information
		Animal animal = service.getAnimalInfoById(id);
		System.out.println(animal);
        model.addAttribute("animal", animal);
        return "edit";
	}
	
	 //to update animal for specific id
	@RequestMapping(path="/animal/update",method=RequestMethod.POST)
    public String updateAnimal(@RequestParam("image") MultipartFile file,
    		@RequestParam("existingImage") String existingImage,
    		@RequestParam("id") Long id,
            @RequestParam("name") String name,
            @RequestParam("category") String category,
            @RequestParam("description") String description,
            @RequestParam("lifeExpectancy") String lifeExpectancy,RedirectAttributes redirectAttributes)
	{
		
		
		try {
			 String storedFileName;
		        
			 if (file.isEmpty()) {
		            // No new file uploaded, use the existing image
		            storedFileName = existingImage;
		        } else {
		            // Store the new file
		            storedFileName = fileStorageService.storeFile(file);
		        }
			
		    Animal animal = new Animal();
			animal.setId(id);
            animal.setName(name);
            animal.setCategory(category);
            animal.setDescription(description);
            animal.setLifeExpectancy(lifeExpectancy);
            animal.setImage(storedFileName);
            
			System.out.println("animal is = "+animal);
			String s=service.updateAnimal(animal); 
			redirectAttributes.addFlashAttribute("successMessage", s);
		}catch(Exception e) {
		    redirectAttributes.addFlashAttribute("errorMessage", "Something went wrong");
		}
        return "redirect:/animal/home"; 
    }
	
	
	//to delete animal of specific id
	@DeleteMapping("/animal/delete/{id}")
    public String deleteAnimal(@PathVariable Long id,Model redirectAttributes) {
	
            service.deleteAnimalById(id);
            redirectAttributes.addAttribute("successMessage", "Deleted Successfully");
            return "redirect:/animal/home";
    }
	
	//to sort animal list by Category-wise
	@GetMapping("/animal/sortCategory")
	public String sortAnimalsByCategory(@RequestParam String category,
	                                    @RequestParam(defaultValue = "0") int page,
	                                    @RequestParam(defaultValue = "5") int size,
	                                    Model model) {
	    Page<Animal> animalPage = service.getAnimalsSortedByCategory(category, page, size);
	    List<Animal> animalList = animalPage.getContent();
	    model.addAttribute("allAnimalsList", animalList);
	    model.addAttribute("currentPage", page);
	    model.addAttribute("totalPages", animalPage.getTotalPages());
	    model.addAttribute("totalItems", animalPage.getTotalElements());
	    return "animal";
	}
	
	
	
	//to sort animal list by Alphabetically
	@GetMapping("/animal/sortAlphabetically")
	public String sortAnimalsAlphabetically(@RequestParam(defaultValue = "0") int page,
	                                        @RequestParam(defaultValue = "5") int size,
	                                        Model model) {
	    Page<Animal> animalPage = service.getAnimalsSortedAlphabetically(page, size);
	    List<Animal> animalList = animalPage.getContent();
	    model.addAttribute("allAnimalsList", animalList);
	    model.addAttribute("currentPage", page);
	    model.addAttribute("totalPages", animalPage.getTotalPages());
	    model.addAttribute("totalItems", animalPage.getTotalElements());
	    return "animal";
	}
	
	
    //to sort animal list by LifeExpectancyRange
	@GetMapping("/animal/sortByLifeExpectancyRange")
	public String sortAnimalsLifeExpectency(@RequestParam(defaultValue = "0") int page,
	                                        @RequestParam(defaultValue = "5") int size,
	                                        Model model) {
	    Page<Animal> animalPage = service.getAnimalsSortedLifeExpectency(page, size);
	    List<Animal> animalList = animalPage.getContent();
	    model.addAttribute("allAnimalsList", animalList);
	    model.addAttribute("currentPage", page);
	    model.addAttribute("totalPages", animalPage.getTotalPages());
	    model.addAttribute("totalItems", animalPage.getTotalElements());
	    return "animal";
	}
}
