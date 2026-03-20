package com.clinicaapi.app.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "medicos")
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String crm;

    @Column(nullable = false)
    private String telefone;

    @Column(nullable = false)
    private String email;

    //TODO: descomentar após merge da feature/consulta
    //@OneToMany(mappedBy = "medico", cascade = CascadeType.ALL)
    //private List<Consulta> consultas;
}
