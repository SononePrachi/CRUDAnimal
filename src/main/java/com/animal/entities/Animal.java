package com.animal.entities;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import org.springframework.web.multipart.MultipartFile;

@Entity
public class Animal {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(unique = true)
	private String name;
	private String category;
	
	@Lob
	private byte[] image;
	private String description;
	private String lifeExpectancy;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLifeExpectancy() {
		return lifeExpectancy;
	}
	public void setLifeExpectancy(String lifeExpectancy) {
		this.lifeExpectancy = lifeExpectancy;
	}
	@Override
	public String toString() {
		return "Animal [id=" + id + ", name=" + name + ", category=" + category + ", image=" + Arrays.toString(image)
				+ ", description=" + description + ", lifeExpectancy=" + lifeExpectancy + "]";
	}
	
	
	
	
	
	
	
	

}
