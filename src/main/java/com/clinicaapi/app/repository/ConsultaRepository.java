package com.clinicaapi.app.repository;

import com.clinicaapi.app.entity.Consulta;
import com.clinicaapi.app.entity.Medico;
import com.clinicaapi.app.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    boolean existsByMedicoAndPacienteAndDataHora(Medico medico, Paciente paciente, LocalDateTime dataHora);

    List<Consulta> findByPacienteId(Long pacienteId);

    List<Consulta> findByMedicoId(Long medicoId);
}