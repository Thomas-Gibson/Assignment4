package com.csc340.assignment4.animals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * AnimalController.java
 * Includes all REST API endpoint mappings for the Animal object.
 */
@Controller
@RequestMapping("/animals")
public class AnimalController {

    @Autowired
    private AnimalService service;

    @GetMapping("showAll")
    public String showAllAnimals(){
        return "redirect:/animals/all";
    }

    /**
     * Get a list of all animals in the database.
     * http://localhost:8080/animals/all
     *
     * @return a list of Animal objects.
     */
    @GetMapping("/all")
    public String getAllAnimals(Model model){
        model.addAttribute("animalList", service.getAllAnimals());
        model.addAttribute("title", "All Animals");

        return "animal-list";
    }

    /**
     * Get a specific Animal by ID.
     * http://localhost:8080/animals/{id}
     *
     * @param id The unique ID of the Animal.
     * @return One Animal object.
     */
    @GetMapping("/{id}")
    public String getAnimalById(@PathVariable int id, Model model) {
        //return service.getAnimalById(id);
        model.addAttribute("animal", service.getAnimalById(id));
        return "animal-details";
    }

    /**
     * Get a list of Animals based on their species.
     * http://localhost:8080/animals/species/{type}
     *
     * @param type The species type to search for.
     * @return A list of Animal objects matching the species type.
     */
    @GetMapping("/species/{type}")
    public String getAnimalsBySpecies(@PathVariable String type, Model model){
        model.addAttribute("animalList", service.getAnimalsBySpecies(type));
        return "animal-list";
    }

    /**
     * Get a list of Animals that contain a specific keyword in their name, species, or description.
     * http://localhost:8080/animals/contains/{keyword}
     *
     * @param keyword The keyword to search for.
     * @return A list of Animal objects that contain the keyword.
     */
    @GetMapping("/contains/{keyword}")
    public String getAnimalsByKeyword(@PathVariable String keyword, Model model) {
        model.addAttribute("animalsList", service.getAnimalByKeyword(keyword));

        return "animal-list";
    }

    /**
     * Serves the create form to the user.
     * @return the html page.
     */
    @GetMapping("/createForm")
    public String showCreateForm() {
        return "animal-create";
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
    public String addNewAnimal(Animal animal) {
        service.addNewAnimal(animal);
        return "redirect:/animals/all";
    }

    /**
     * Serves the update form to the user.
     * @return the html page.
     */
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable int id, Model model) {
        model.addAttribute("animal", service.getAnimalById(id));
        return "animal-update";
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
     * @param animal The updated Animal details.
     * @return The updated Animal object.
     */
    @PostMapping("/update")
    public String updateAnimal(Animal animal) {
        service.addNewAnimal(animal);
        return "redirect:/animals/" + animal.getAnimalId();
    }

    /**
     * Delete an Animal by its unique ID.
     * http://localhost:8080/animals/delete/{id}
     *
     * @param id The unique ID of the Animal to delete.
     * @return The updated list of Animals.
     */
    @GetMapping("/delete/{id}")
    public String deleteAnimalById(@PathVariable int id) {
        service.deleteEntityById(id);
        return "redirect:/animals/all";
    }
}
