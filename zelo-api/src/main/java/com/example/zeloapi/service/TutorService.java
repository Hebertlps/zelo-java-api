package com.example.zeloapi.service;

import com.example.zeloapi.model.Tutor;
import com.example.zeloapi.repository.TutorRepository;
import com.example.zeloapi.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TutorService {

    @Autowired
    private TutorRepository tutorRepository;

    public Page<Tutor> getAllTutores(Pageable pageable) {
        return tutorRepository.findAll(pageable);
    }

    public Tutor getTutorById(Long id) {
        return tutorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tutor não encontrado com id: " + id));
    }

    public Tutor createTutor(Tutor tutor) {
        return tutorRepository.save(tutor);
    }

    public Tutor updateTutor(Long id, Tutor tutorDetails) {
        Tutor tutor = getTutorById(id);
        
        tutor.setNome(tutorDetails.getNome());
        tutor.setEmail(tutorDetails.getEmail());
        tutor.setTelefone(tutorDetails.getTelefone());
        if (tutorDetails.getSenha() != null && !tutorDetails.getSenha().isEmpty()) {
            tutor.setSenha(tutorDetails.getSenha());
        }
        
        return tutorRepository.save(tutor);
    }

    public void deleteTutor(Long id) {
        Tutor tutor = getTutorById(id);
        tutorRepository.delete(tutor);
    }
}
