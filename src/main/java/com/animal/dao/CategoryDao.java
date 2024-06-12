package com.animal.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.animal.entities.Category;

public interface CategoryDao extends JpaRepository<Category, Integer> {

	public Category findByName(String name);
    public Category findById(Long id);
}
