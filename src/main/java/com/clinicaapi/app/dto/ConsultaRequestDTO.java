package com.clinicaapi.app.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ConsultaRequestDTO {
    @NotNull(message = "O paciente e obrigatorio")
    private Long pacienteId;

    @NotNull(message = "O medico e obrigatorio")
    private Long medicoId;

    @NotNull(message = "A data e hora sao obrigatorias")
    @Future(message = "A consulta deve ser agendada para uma data futura")
    private LocalDateTime dataHora;

    @NotBlank(message = "O status e obrigatorio")
    private String status;

    private String observacoes;
}
