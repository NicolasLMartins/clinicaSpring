package com.clinicaapi.app.service;

import com.clinicaapi.app.dto.ConsultaDTO;
import com.clinicaapi.app.dto.ConsultaRequestDTO;
import com.clinicaapi.app.entity.Consulta;
import com.clinicaapi.app.entity.Medico;
import com.clinicaapi.app.entity.Paciente;
import com.clinicaapi.app.exception.BusinessException;
import com.clinicaapi.app.exception.ResourceNotFoundException;
import com.clinicaapi.app.repository.ConsultaRepository;
import com.clinicaapi.app.repository.MedicoRepository;
import com.clinicaapi.app.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final PacienteRepository pacienteRepository;
    private final MedicoRepository medicoRepository;

    private ConsultaDTO converterParaDTO(Consulta consulta) {
        return new ConsultaDTO(
                consulta.getId(),
                consulta.getPaciente().getId(),
                consulta.getPaciente().getNome(),
                consulta.getMedico().getId(),
                consulta.getMedico().getNome(),
                consulta.getDataHora(),
                consulta.getStatus(),
                consulta.getObservacoes()
        );
    }

    public List<ConsultaDTO> listarTodas() {
        return consultaRepository.findAll()
                .stream()
                .map(this::converterParaDTO)
                .toList();
    }

    public ConsultaDTO buscarPorId(Long id) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consulta nao encontrada com id: " + id));
        return converterParaDTO(consulta);
    }

    public List<ConsultaDTO> listarPorPaciente(Long pacienteId) {
        if (!pacienteRepository.existsById(pacienteId)) {
            throw new ResourceNotFoundException("Paciente nao encontrado com id: " + pacienteId);
        }
        return consultaRepository.findByPacienteId(pacienteId)
                .stream()
                .map(this::converterParaDTO)
                .toList();
    }

    public List<ConsultaDTO> listarPorMedico(Long medicoId) {
        if (!medicoRepository.existsById(medicoId)) {
            throw new ResourceNotFoundException("Medico nao encontrado com id: " + medicoId);
        }
        return consultaRepository.findByMedicoId(medicoId)
                .stream()
                .map(this::converterParaDTO)
                .toList();
    }

    public ConsultaDTO agendar(ConsultaRequestDTO dto) {
        Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Paciente nao encontrado com id: " + dto.getPacienteId()));

        Medico medico = medicoRepository.findById(dto.getMedicoId())
                .orElseThrow(() -> new ResourceNotFoundException("Medico nao encontrado com id: " + dto.getMedicoId()));

        boolean jaExiste = consultaRepository.existsByMedicoAndPacienteAndDataHora(medico, paciente, dto.getDataHora());

        if (jaExiste) {
            throw new BusinessException("Ja existe uma consulta agendada para esse paciente com esse medico nesse horario.");
        }

        Consulta consulta = new Consulta();
        consulta.setPaciente(paciente);
        consulta.setMedico(medico);
        consulta.setDataHora(dto.getDataHora());
        consulta.setStatus(dto.getStatus());
        consulta.setObservacoes(dto.getObservacoes());

        return converterParaDTO(consultaRepository.save(consulta));
    }

    public ConsultaDTO atualizar(Long id, ConsultaRequestDTO dto) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consulta nao encontrada com id: " + id));

        Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Paciente nao encontrado com id: " + dto.getPacienteId()));

        Medico medico = medicoRepository.findById(dto.getMedicoId())
                .orElseThrow(() -> new ResourceNotFoundException("Medico nao encontrado com id: " + dto.getMedicoId()));

        consulta.setPaciente(paciente);
        consulta.setMedico(medico);
        consulta.setDataHora(dto.getDataHora());
        consulta.setStatus(dto.getStatus());
        consulta.setObservacoes(dto.getObservacoes());

        return converterParaDTO(consultaRepository.save(consulta));
    }

    public void cancelar(Long id) {
        if (!consultaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Consulta nao encontrada com id: " + id);
        }
        consultaRepository.deleteById(id);
    }
}