package com.animal.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Animal {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    private String image;

    private String description;

    @ManyToOne
    @JoinColumn(name = "life_expectancy_id", nullable = false)
    private LifeExpectancy lifeExpectancy;

    // Getters and Setters

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public LifeExpectancy getLifeExpectancy() {
        return lifeExpectancy;
    }

    public void setLifeExpectancy(LifeExpectancy lifeExpectancy) {
        this.lifeExpectancy = lifeExpectancy;
    }

    @Override
    public String toString() {
        return "Animal [id=" + id + ", name=" + name + ", category=" + category + ", image=" + image + ", description="
                + description + ", lifeExpectancy=" + lifeExpectancy + "]";
    }
}
