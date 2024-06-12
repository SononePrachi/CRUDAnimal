package com.animal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.animal.dao.LifeExpectancyDao;
import com.animal.entities.LifeExpectancy;

@Service
public class LifeExpectancyService {
	@Autowired
    private LifeExpectancyDao lifeExpectancyDao;

    public List<LifeExpectancy> getAllLifeExpectancies() {
        return lifeExpectancyDao.findAll();
    }

    public LifeExpectancy getLifeExpectancyById(Long id) {
        return lifeExpectancyDao.findById(id).orElse(null);
    }
}
