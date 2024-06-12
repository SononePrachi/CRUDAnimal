package com.animal.entities;

public class AnimalDto {
    private Long id;
    private String name;
    private Long categoryId; 
    private String categoryName;
    private Long lifeExpectancyId;
    private String lifeExpectancyName;
    private String image;
    private String description;

    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Long getLifeExpectancyId() {
		return lifeExpectancyId;
	}

	public void setLifeExpectancyId(Long lifeExpectancyId) {
		this.lifeExpectancyId = lifeExpectancyId;
	}

	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getLifeExpectancyName() {
		return lifeExpectancyName;
	}

	public void setLifeExpectancyName(String lifeExpectancyName) {
		this.lifeExpectancyName = lifeExpectancyName;
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


	public AnimalDto(Long id, String name, Long categoryId, String categoryName,Long lifeExpectancyId, String lifeExpectancyName,
			String image, String description) {
		super();
		this.id = id;
		this.name = name;
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.lifeExpectancyId = lifeExpectancyId;
		this.lifeExpectancyName = lifeExpectancyName;
		this.image = image;
		this.description = description;
	}
}

