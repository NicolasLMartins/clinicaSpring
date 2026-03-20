package com.clinicaapi.app.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MedicoDTO {
    private Long id;

    @NotBlank(message = "O nome e obrigatorio!")
    private String nome;

    @NotBlank(message = "O crm e obrigatorio!")
    private String crm;

    @NotBlank(message = "O telefone e obrigatorio!")
    private String telefone;

    @Email(message = "Informe um email valido!")
    private String email;
}
