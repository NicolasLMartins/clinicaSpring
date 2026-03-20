package com.clinicaapi.app.service;

import com.clinicaapi.app.dto.MedicoDTO;
import com.clinicaapi.app.entity.Medico;
import com.clinicaapi.app.exception.BusinessException;
import com.clinicaapi.app.exception.ResourceNotFoundException;
import com.clinicaapi.app.repository.MedicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicoService {
    private final MedicoRepository medicoRepository;

    private MedicoDTO converterParaDTO(Medico medico){
        return new MedicoDTO(
                medico.getId(),
                medico.getNome(),
                medico.getCrm(),
                medico.getTelefone(),
                medico.getEmail()
        );
    }

    public MedicoDTO cadastrar(MedicoDTO dto){
        if(medicoRepository.existsByCrm(dto.getCrm())){
            throw new BusinessException("CRM ja cadastrado!");
        }

        Medico novoMedico = new Medico();
        novoMedico.setNome(dto.getNome());
        novoMedico.setCrm(dto.getCrm());
        novoMedico.setTelefone(dto.getTelefone());
        novoMedico.setEmail(dto.getEmail());

        return converterParaDTO(medicoRepository.save(novoMedico));
    }

    public List<MedicoDTO> listarTodos(){
        return medicoRepository.findAll()
                .stream()
                .map(this::converterParaDTO)
                .toList();
    }

    public MedicoDTO buscarPorId(Long id){
        return converterParaDTO(medicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medico nao encontrado!")));
    }

    public MedicoDTO atualizar(Long id, MedicoDTO dto){
        Medico medicoAtualizado = medicoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medico nao encontrado!"));

        medicoAtualizado.setCrm(dto.getCrm());
        medicoAtualizado.setNome(dto.getNome());
        medicoAtualizado.setTelefone(dto.getTelefone());
        medicoAtualizado.setEmail(dto.getEmail());

        return converterParaDTO(medicoRepository.save(medicoAtualizado));

    }

    public void deletar(Long id){
        if(!medicoRepository.existsById(id)){
            throw new ResourceNotFoundException("Medico nao encontrado!");
        }

        medicoRepository.deleteById(id);
    }
}
