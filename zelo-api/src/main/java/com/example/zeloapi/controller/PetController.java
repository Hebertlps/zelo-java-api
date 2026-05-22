package com.example.zeloapi.controller;

import com.example.zeloapi.model.Pet;
import com.example.zeloapi.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/pets")
public class PetController {

    @Autowired
    private PetService petService;

    @GetMapping
    @Cacheable("pets")
    public ResponseEntity<Page<Pet>> getAllPets(Pageable pageable) {
        return ResponseEntity.ok(petService.getAllPets(pageable));
    }

    @GetMapping("/{id}")
    @Cacheable(value = "pet", key = "#id")
    public ResponseEntity<Pet> getPetById(@PathVariable Long id) {
        return ResponseEntity.ok(petService.getPetById(id));
    }

    @PostMapping
    @CacheEvict(value = {"pets", "pet"}, allEntries = true)
    public ResponseEntity<Pet> createPet(@Valid @RequestBody Pet pet) {
        return new ResponseEntity<>(petService.createPet(pet), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @CacheEvict(value = {"pets", "pet"}, allEntries = true)
    public ResponseEntity<Pet> updatePet(@PathVariable Long id, @Valid @RequestBody Pet petDetails) {
        return ResponseEntity.ok(petService.updatePet(id, petDetails));
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = {"pets", "pet"}, allEntries = true)
    public ResponseEntity<Void> deletePet(@PathVariable Long id) {
        petService.deletePet(id);
        return ResponseEntity.noContent().build();
    }
}
