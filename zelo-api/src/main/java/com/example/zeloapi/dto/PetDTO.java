package com.example.zeloapi.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Data
public class PetDTO {
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank(message = "Espécie é obrigatória")
    private String especie;

    private String raca;

    @NotNull(message = "Idade é obrigatória")
    @Positive(message = "Idade deve ser positiva")
    private Integer idade;

    @NotNull(message = "Peso é obrigatório")
    @Positive(message = "Peso deve ser positivo")
    private Double peso;

    @NotNull(message = "Tutor ID é obrigatório")
    private Long tutorId;
}
