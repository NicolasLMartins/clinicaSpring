package com.clinicaapi.app.service;

import com.clinicaapi.app.dto.PacienteDTO;
import com.clinicaapi.app.entity.Paciente;
import com.clinicaapi.app.exception.BusinessException;
import com.clinicaapi.app.exception.ResourceNotFoundException;
import com.clinicaapi.app.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PacienteService {
    private final PacienteRepository pacienteRepository;

    private PacienteDTO converterParaDTO(Paciente paciente){
        return new PacienteDTO(
                paciente.getId(),
                paciente.getNome(),
                paciente.getCpf(),
                paciente.getTelefone(),
                paciente.getEmail(),
                paciente.getDataNascimento()
        );
    }

    public PacienteDTO cadastrar(PacienteDTO dto){
        if (pacienteRepository.existsByCpf(dto.getCpf())){
            throw new BusinessException("CPF ja cadastrado!");
        }

        Paciente novoPaciente = new Paciente();
        novoPaciente.setNome(dto.getNome());
        novoPaciente.setCpf(dto.getCpf());
        novoPaciente.setTelefone(dto.getTelefone());
        novoPaciente.setEmail(dto.getEmail());
        novoPaciente.setDataNascimento(dto.getDataNascimento());

        return converterParaDTO(pacienteRepository.save(novoPaciente));
    }

    public List<PacienteDTO> listarTodos(){
        return pacienteRepository.findAll()
                .stream()
                .map(this::converterParaDTO)
                .toList();
    }

    public PacienteDTO buscarPorId(Long id){
        return converterParaDTO(pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente nao encontrado!")));
    }

    public PacienteDTO atualizar(Long id, PacienteDTO dto){
        Paciente pacienteAtualizado = pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente nao encontrado!"));

        pacienteAtualizado.setNome(dto.getNome());
        pacienteAtualizado.setCpf(dto.getCpf());
        pacienteAtualizado.setTelefone(dto.getTelefone());
        pacienteAtualizado.setEmail(dto.getEmail());
        pacienteAtualizado.setDataNascimento(dto.getDataNascimento());

        return converterParaDTO(pacienteRepository.save(pacienteAtualizado));
    }

    public void deletar(Long id){
        if (!pacienteRepository.existsById(id)){
            throw new ResourceNotFoundException("Paciente nao encontrado!");
        }

        pacienteRepository.deleteById(id);
    }
}
