package com.example.zeloapi.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Data
@Entity
@Table(name = "t_ch_tutores")
public class Tutor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tutores")
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
    @Column(name = "nm_tutores", nullable = false, length = 100)
    private String nome;
    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    @Column(name = "ds_email", nullable = false, unique = true, length = 100)
    private String email;
    @Column(name = "ds_senha", nullable = false, length = 255)
    private String senha;
    @NotBlank(message = "Telefone é obrigatório")
    @Column(name = "ds_telefone", length = 20)
    private String telefone;

    @OneToMany(mappedBy = "tutor", cascade = CascadeType.ALL)
    private List<Pet> pets;
}
