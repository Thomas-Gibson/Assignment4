package com.csc340.assignment4.animals;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer> {

    @Query(value = "select * from animals a where a.species = ?1", nativeQuery = true)
    List<Animal> getAnimalsBySpecies(String species);

    @Query(value = "select * from animals a where a.name like %?1%", nativeQuery = true)
    List<Animal> getAnimalByKeyword(String keyWord);
}
