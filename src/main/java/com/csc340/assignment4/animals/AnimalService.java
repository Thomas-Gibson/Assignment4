package com.csc340.assignment4.animals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * AnimalService.java
 * Centralizes data access to the Animal Database.
 */
@Service
public class AnimalService {

    @Autowired
    private AnimalRepository repo;

    /**
     * Fetch all Animals.
     *
     * @return the list of all Animals.
     */
    public List<Animal> getAllAnimals() {
        return repo.findAll();
    }

    /**
     * Fetch a unique Animal by its ID.
     *
     * @param animalId the unique Animal ID.
     * @return a unique Animal object, or null if not found.
     */
    public Animal getAnimalById(int animalId) {
        return repo.findById(animalId).orElse(null);
    }

    /**
     * Add a new Animal to the database.
     *
     * @param animal the new Animal to add.
     */
    public void addNewAnimal(Animal animal) {
        repo.save(animal);
    }

    /**
     * Update an existing Animal's details.
     *
     * @param id the unique Animal ID.
     * @param animal the new Animal details.
     */
    public void updateAnimal(int id, Animal animal) {
        Animal existing = getAnimalById(id);
        if (existing != null) {
            existing.setName(animal.getName());
            existing.setScientificName(animal.getScientificName());
            existing.setSpecies(animal.getSpecies());
            existing.setHabitat(animal.getHabitat());
            existing.setDescription(animal.getDescription());

            repo.save(existing);
        }
    }

    /**
     * Delete an Animal by its unique ID.
     *
     * @param id the unique Animal ID.
     */
    public void deleteEntityById(int id) {
        repo.deleteById(id);
    }

    /**
     * Fetch all Animals that match the given species.
     *
     * @param species the species to search for.
     * @return the list of matching Animals.
     */
    public List<Animal> getAnimalsBySpecies(String species) {
        return repo.getAnimalsBySpecies(species);
    }

    /**
     * Fetch all Animals that match a given keyword in their name, species, or description.
     *
     * @param keyword the keyword to search for.
     * @return the list of matching Animals.
     */
    public List<Animal> getAnimalByKeyword(String keyword) {
        return repo.getAnimalByKeyword(keyword);
    }
}
