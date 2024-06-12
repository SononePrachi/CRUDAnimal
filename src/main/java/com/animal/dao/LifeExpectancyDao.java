package com.animal.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.animal.entities.LifeExpectancy;

public interface LifeExpectancyDao extends JpaRepository<LifeExpectancy, Long>{

}
