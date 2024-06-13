package com.animal.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.animal.entities.Animal;
import com.animal.entities.AnimalDto;
import com.animal.entities.Category;
import com.animal.entities.LifeExpectancy;
import com.animal.service.AnimalService;
import com.animal.service.CategoryService;
import com.animal.service.FileStorageService;
import com.animal.service.LifeExpectancyService;


@Controller
public class AnimalController {
	
	
	@Autowired
    private FileStorageService fileStorageService;
	
	@Autowired
	private AnimalService service;
	
	@Autowired
    private CategoryService categoryService;

    @Autowired
    private LifeExpectancyService lifeExpectancyService;
	
	
	@GetMapping("/animal/home")
    public String homePage( @RequestParam(required = false) String category,
            				@RequestParam(defaultValue = "name") String sortBy,
            				@RequestParam(defaultValue = "0") int page, 
                            @RequestParam(defaultValue = "5") int size, 
                           Model m) {
        Page<AnimalDto> animalPage = service.getAllAnimals(category,sortBy,page, size);
        List<AnimalDto> animalList = animalPage.getContent();
        
        System.out.println(" HOME Animal List = "+animalList);
        m.addAttribute("allAnimalsList", animalList);
        m.addAttribute("currentPage", page);
        m.addAttribute("totalPages", animalPage.getTotalPages());
        m.addAttribute("totalItems", animalPage.getTotalElements());
        m.addAttribute("categories", categoryService.getAllCategories());
        m.addAttribute("lifeExpectancies", lifeExpectancyService.getAllLifeExpectancies());

        return "animal";
    }
	
	
	 //to show edit page for update specific animal
	 @RequestMapping("/animal/edit/{id}")
	 //@PathVariable  it means that you are expecting a value from the URI that will be mapped to the id parameter of your method.
	public String editAnimalShow(@PathVariable Long id, Model model)
	{	
		System.out.println("id="+id);
		//to get specific id information
		AnimalDto animal = service.getAnimalInfoById(id);
		System.out.println("Edit Animal = "+animal);
        model.addAttribute("animal", animal);
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("lifeExpectancies", lifeExpectancyService.getAllLifeExpectancies());
        return "edit";
	}
	
	 @RequestMapping(path="/animal/save",method=RequestMethod.POST)
	    public String saveOrUpdateAnimal(@RequestParam("image") MultipartFile file,
	    		@RequestParam(required = false) String existingImage,
	    		@RequestParam(required = false) Long id,
	            @RequestParam("name") String name,
	            @RequestParam("categoryId") Long categoryId,
	            @RequestParam("description") String description,
	            @RequestParam("lifeExpectancyId") Long lifeExpectancyId,RedirectAttributes redirectAttributes) {
	        
		    //Checks if a new file was uploaded. If not,it uses the existing image. 
		   //Otherwise, it stores the new file using FileStorageService.
	        try {
	        	String storedFileName;
		        
				 if (file.isEmpty()) {
			            // No new file uploaded, use the existing image
			            storedFileName = existingImage;
			        } else {
			            // Store the new file
			            storedFileName = fileStorageService.storeFile(file);
			        }
				 Category category = categoryService.getCategoryById(categoryId);
		         LifeExpectancy lifeExpectancy = lifeExpectancyService.getLifeExpectancyById(lifeExpectancyId);
			    
		        Animal animal = new Animal();
				animal.setId(id);
	            animal.setName(name);
	            animal.setCategory(category);
	            animal.setDescription(description);
	            animal.setLifeExpectancy(lifeExpectancy);
	            animal.setImage(storedFileName);
	            
				System.out.println("animal is = "+animal);
				service.save(animal); 
	            redirectAttributes.addFlashAttribute("successMessage", "Animal saved successfully.");
	        } catch (Exception e) {
	            redirectAttributes.addFlashAttribute("errorMessage", "Error saving animal: " + e.getMessage());
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
	
}
