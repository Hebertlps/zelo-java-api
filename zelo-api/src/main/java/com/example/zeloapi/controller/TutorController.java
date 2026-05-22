package com.example.zeloapi.controller;

import com.example.zeloapi.model.Tutor;
import com.example.zeloapi.service.TutorService;
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
@RequestMapping("/api/tutores")
public class TutorController {

    @Autowired
    private TutorService tutorService;

    @GetMapping
    @Cacheable("tutores")
    public ResponseEntity<Page<Tutor>> getAllTutores(Pageable pageable) {
        return ResponseEntity.ok(tutorService.getAllTutores(pageable));
    }

    @GetMapping("/{id}")
    @Cacheable(value = "tutor", key = "#id")
    public ResponseEntity<Tutor> getTutorById(@PathVariable Long id) {
        return ResponseEntity.ok(tutorService.getTutorById(id));
    }

    @PostMapping
    @CacheEvict(value = {"tutores", "tutor"}, allEntries = true)
    public ResponseEntity<Tutor> createTutor(@Valid @RequestBody Tutor tutor) {
        return new ResponseEntity<>(tutorService.createTutor(tutor), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @CacheEvict(value = {"tutores", "tutor"}, allEntries = true)
    public ResponseEntity<Tutor> updateTutor(@PathVariable Long id, @Valid @RequestBody Tutor tutorDetails) {
        return ResponseEntity.ok(tutorService.updateTutor(id, tutorDetails));
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = {"tutores", "tutor"}, allEntries = true)
    public ResponseEntity<Void> deleteTutor(@PathVariable Long id) {
        tutorService.deleteTutor(id);
        return ResponseEntity.noContent().build();
    }
}
