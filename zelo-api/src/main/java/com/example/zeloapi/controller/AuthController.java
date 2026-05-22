package com.example.zeloapi.controller;

import com.example.zeloapi.model.Tutor;
import com.example.zeloapi.repository.TutorRepository;
import com.example.zeloapi.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private TutorRepository tutorRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String senha = credentials.get("senha");

        Optional<Tutor> tutorOpt = tutorRepository.findByEmail(email);

        if (tutorOpt.isPresent() && tutorOpt.get().getSenha().equals(senha)) {
            String token = jwtUtil.generateToken(email);
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("tutorId", tutorOpt.get().getId());
            response.put("nome", tutorOpt.get().getNome());
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(401).body("Credenciais inválidas");
    }
}
