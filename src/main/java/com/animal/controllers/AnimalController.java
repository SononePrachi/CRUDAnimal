package com.animal.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.animal.entities.Animal;
import com.animal.service.AnimalService;

@Controller
public class AnimalController {
	
	@Value("${uploadDir}")
	private String uploadFolder;
	
	@Autowired
	private AnimalService service;
	
	@GetMapping("/animal/home")
	public String homePage(Model m)
	{
		List<Animal> l=new ArrayList<Animal>();
		l=service.getAllAnimals();
		System.out.println(l);
		m.addAttribute("allAnimalsList",l);
		return "animal";
	}
	
	@PostMapping("/animal/addAnimal")
	public String addAnimal(@ModelAttribute Animal animal,RedirectAttributes redirectAttributes)
	{
		String status;
		System.out.println(animal);
		try {
			
//			if (!imageFile.isEmpty()) 
//			{
//				byte[] imageData = imageFile.getBytes();
//				animal.setImage(imageData); 
//			}
		    status=service.addAnimal(animal);
		    redirectAttributes.addFlashAttribute("successMessage", "Animal added Successfully");
		    
	    }catch(Exception e) {
	    	status=(e.getMessage());
	    	redirectAttributes.addFlashAttribute("errorMessage", "Something went wrong");
	    }
		redirectAttributes.addFlashAttribute("status",status);
		return "redirect:/animal/home";	
	}
	
	@RequestMapping("/animal/edit/{id}")
	public String editAnimalShow(@PathVariable Long id, Model model)
	{	
		System.out.println("id="+id);
		Animal animal = service.getAnimalInfoById(id);
		System.out.println(animal);
        model.addAttribute("animal", animal);
        return "edit";
	}
	
	@RequestMapping(path="/animal/update",method=RequestMethod.POST)
    public String updateAnimal(@ModelAttribute Animal animal,RedirectAttributes redirectAttributes)
	{
        String s=service.updateAnimal(animal); 
        redirectAttributes.addFlashAttribute("successMessage", s);
        return "redirect:/animal/home"; 
    }
	
	
	@DeleteMapping("/animal/delete/{id}")
    public String deleteAnimal(@PathVariable Long id,Model redirectAttributes) {
	
            service.deleteAnimalById(id);
            redirectAttributes.addAttribute("successMessage", "Deleted Successfully");
            return "redirect:/animal/home";
    }
	
	@GetMapping("/animal/sortCategory")
    public String sortAnimalsByCategory(@RequestParam String category, Model model) {
        List<Animal> animalList = service.getAnimalsSortedByCategory(category);
        System.out.println(animalList);
        model.addAttribute("allAnimalsList", animalList);
        return "animal";
    }
	
	
	@GetMapping("/animal/sortAlphabetically")
    public String sortAnimalsAlphabetically(Model model) {
        List<Animal> animalList = service.getAnimalsSortedAlphabetically();
        model.addAttribute("allAnimalsList", animalList);
        return "animal";
    }
	
	

	@GetMapping("/animal/sortByLifeExpectancyRange")
    public String sortAnimalsLifeExpectency(Model model) {
        List<Animal> animalList = service.getAnimalsSortedLifeExpectency();
        model.addAttribute("allAnimalsList", animalList);
        return "animal";
    }
	
}
