# Animal CRUD Project Using Spring Boot
This is Spring Boot Application for managing a database of animals providing a crud operation via RestFul API.Additionally it includes web pages for submitting and listing animal information.


## Features

- Submit new animal information through form.
- Editing existing animal information.
- Sorting all animals by alphabetically,by LifeExpectancy and by Category
- Captcha for security purpose


## Requirements
Java 17 or later
A database(MySqL)


## Setup Instructions
DataBase Configuration
Do the database setting in application.property
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.datasource.url=jdbc:mysql://localhost:3306/animals
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.username=root
spring.datasource.password=root
server.port=8080


## Running the Application
Right Click on Project ->Run as->Java Application


## Web Pages
All Animals
URL:animal/home

Edit Animals
URL:animal/edit/{id}


## API
to getting the home page with all animal's list
@GetMapping("/animal/home")

to add animal
@PostMapping("/animal/addAnimal")

to show edit page for update specific animal
 @RequestMapping("/animal/edit/{id}")

to update animal for specific id
@RequestMapping(path="/animal/update",method=RequestMethod.POST)

to delete animal of specific id
@DeleteMapping("/animal/delete/{id}")

to sort animal list by Category-wise
@GetMapping("/animal/sortCategory")

//to sort animal list by Alphabetically
@GetMapping("/animal/sortAlphabetically")

to sort animal list by LifeExpectancyRange
@GetMapping("/animal/sortByLifeExpectancyRange")


## Database Configuration
The application uses Spring Data JPA to interact with the database.Ensure your datbase credentials are correctly set in application.property file.You can also adjust Hibernate Settings according to your development environment.


## Java Settings
use java 17 to build and the run the application


## TroubleShooting
port conflicts:If the default port 8080 is already in use,change it in application.property file.
server.port=8081