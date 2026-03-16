package com.clinicaapi.app.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PacienteDTO {
    private Long id;

    @NotBlank(message = "O nome e obrigatorio!")
    private String nome;

    @NotBlank(message = "O cpf e obrigatorio!")
    private String cpf;

    @NotBlank(message = "O telefone e obrigatorio!")
    private String telefone;

    @Email(message = "Informe um email valido!")
    private String email;

    @NotNull(message = "A data de nascimento e obrigatória!")
    @Past
    private LocalDate dataNascimento;
}
