package com.csc340.assignment4.animals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * AnimalController.java
 * Includes all REST API endpoint mappings for the Animal object.
 */
@RestController
@RequestMapping("/animals")
public class AnimalController {

    @Autowired
    private AnimalService service;

    /**
     * Get a list of all animals in the database.
     * http://localhost:8080/animals/all
     *
     * @return a list of Animal objects.
     */
    @GetMapping("/all")
    public List<Animal> getAllAnimals() {
        return service.getAllAnimals();
    }

    /**
     * Get a specific Animal by ID.
     * http://localhost:8080/animals/{id}
     *
     * @param id The unique ID of the Animal.
     * @return One Animal object.
     */
    @GetMapping("/{id}")
    public Animal getAnimalById(@PathVariable int id) {
        return service.getAnimalById(id);
    }

    /**
     * Get a list of Animals based on their species.
     * http://localhost:8080/animals/species/{type}
     *
     * @param type The species type to search for.
     * @return A list of Animal objects matching the species type.
     */
    @GetMapping("/species/{type}")
    public List<Animal> getAnimalsBySpecies(@PathVariable String type) {
        return service.getAnimalsBySpecies(type);
    }

    /**
     * Get a list of Animals that contain a specific keyword in their name, species, or description.
     * http://localhost:8080/animals/contains/{keyword}
     *
     * @param keyword The keyword to search for.
     * @return A list of Animal objects that contain the keyword.
     */
    @GetMapping("/contains/{keyword}")
    public List<Animal> getAnimalsByKeyword(@PathVariable String keyword) {
        return service.getAnimalByKeyword(keyword);
    }

    /**
     * Create a new Animal entry.
     * http://localhost:8080/animals/new
     *
     * -- data
     * {
     *      "animalId": 8000,
     *      "name": "Cricket",
     *      "scientificName": "Gryllidae",
     *      "species": "insect",
     *      "habitat": "They're everywhere",
     *      "description": "Chirping creature"
     * }
     *
     * @param animal The Animal object to create.
     * @return The updated list of Animals.
     */
    @PostMapping("/new")
    public List<Animal> addNewAnimal(@RequestBody Animal animal) {
        service.addNewAnimal(animal);
        return service.getAllAnimals();
    }

    /**
     * Update an existing Animal object.
     * http://localhost:8080/animals/update/{id}
     *
     * --data
     * {
     *      "animalId": 8001,
     *      "name": "Updated Cricket",
     *      "scientificName": "Updated Gryllidae",
     *      "species": "Updated insect",
     *      "habitat": "Updated habitat",
     *      "description": "Updated description"
     * }
     *
     * @param id The unique ID of the Animal to update.
     * @param animal The updated Animal details.
     * @return The updated Animal object.
     */
    @PutMapping("/update/{id}")
    public Animal updateAnimal(@PathVariable int id, @RequestBody Animal animal) {
        service.updateAnimal(id, animal);
        return service.getAnimalById(id);
    }

    /**
     * Delete an Animal by its unique ID.
     * http://localhost:8080/animals/delete/{id}
     *
     * @param id The unique ID of the Animal to delete.
     * @return The updated list of Animals.
     */
    @DeleteMapping("/delete/{id}")
    public List<Animal> deleteAnimalById(@PathVariable int id) {
        service.deleteEntityById(id);
        return service.getAllAnimals();
    }
}
