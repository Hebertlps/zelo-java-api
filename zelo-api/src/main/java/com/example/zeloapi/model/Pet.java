package com.example.zeloapi.model;

import jakarta.persistence.*;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Data
@Entity
@Table(name = "t_ch_pets")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pets")
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    @Column(name = "nm_pets", nullable = false, length = 100)
    private String nome;
    @NotBlank(message = "Espécie é obrigatória")
    @Column(name = "ds_especie", nullable = false, length = 50)
    private String especie;
    @Column(name = "ds_raca", length = 50)
    private String raca;
    @NotNull(message = "Idade é obrigatória")
    @Positive(message = "Idade deve ser positiva")
    @Column(name = "nr_idade")
    private Integer idade;
    @NotNull(message = "Peso é obrigatório")
    @Positive(message = "Peso deve ser positivo")
    @Column(name = "nr_peso", precision = 5, scale = 2)
    private Double peso;

    @ManyToOne
    @JoinColumn(name = "id_tutores", nullable = false)
    private Tutor tutor;
}
