package com.esgaspar.programacao.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "privilegio")
public class Privilegio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descricao", nullable = false, length = 50)
    private String descricao;

    @Column(name = "codigo", length = 50)
    private String codigo;

    @Column(name = "ordem")
    private Integer ordem;

    @OrderBy("nome")
    @ManyToMany(mappedBy = "privilegios",
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Voluntario> voluntarioList;
}
