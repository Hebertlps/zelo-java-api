package com.example.zeloapi.service;

import com.example.zeloapi.model.Pet;
import com.example.zeloapi.repository.PetRepository;
import com.example.zeloapi.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    public Page<Pet> getAllPets(Pageable pageable) {
        return petRepository.findAll(pageable);
    }

    public Pet getPetById(Long id) {
        return petRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pet não encontrado com id: " + id));
    }

    public Pet createPet(Pet pet) {
        return petRepository.save(pet);
    }

    public Pet updatePet(Long id, Pet petDetails) {
        Pet pet = getPetById(id);
        
        pet.setNome(petDetails.getNome());
        pet.setEspecie(petDetails.getEspecie());
        pet.setRaca(petDetails.getRaca());
        pet.setIdade(petDetails.getIdade());
        pet.setPeso(petDetails.getPeso());
        
        return petRepository.save(pet);
    }

    public void deletePet(Long id) {
        Pet pet = getPetById(id);
        petRepository.delete(pet);
    }
}
