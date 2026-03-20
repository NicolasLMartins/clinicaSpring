package com.clinicaapi.app.controller;

import com.clinicaapi.app.dto.MedicoDTO;
import com.clinicaapi.app.service.MedicoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicos")
@RequiredArgsConstructor
public class MedicoController {
    private final MedicoService medicoService;

    @PostMapping
    public ResponseEntity<MedicoDTO> cadastrar(@Valid @RequestBody MedicoDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(medicoService.cadastrar(dto));
    }

    @GetMapping
    public ResponseEntity<List<MedicoDTO>> listarTodos(){
        return ResponseEntity.ok(medicoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoDTO> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(medicoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicoDTO> atualizar(@PathVariable Long id, @Valid @RequestBody MedicoDTO dto){
        return ResponseEntity.ok(medicoService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        medicoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
