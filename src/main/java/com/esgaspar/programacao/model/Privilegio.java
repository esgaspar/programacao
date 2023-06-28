package com.esgaspar.programacao.model;

import com.esgaspar.programacao.model.dto.PrivilegioDto;
import com.esgaspar.programacao.model.dto.VoluntarioDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "privilegio")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Privilegio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "descricao", nullable = false, length = 50)
    private String descricao;

    @Column(name = "codigo", length = 50)
    private String codigo;

    @Column(name = "ordem")
    private Integer ordem;

    @ManyToMany
    @JoinTable(name = "voluntario_has_privilegio", joinColumns =
            {@JoinColumn(name = "privilegio_id")}, inverseJoinColumns =
            {@JoinColumn(name = "voluntario_id")})
    private List<Voluntario> voluntarioList;


    @JsonIgnore
    public PrivilegioDto getDtoParcial() {
        return getDto(false);
    }

    @JsonIgnore
    public PrivilegioDto getDto() {
        return getDto(true);
    }

    @JsonIgnore
    private PrivilegioDto getDto(boolean includePrivilegio) {
        PrivilegioDto dto = PrivilegioDto.builder()
                .id(getId())
                .descricao(getDescricao())
                .codigo(getCodigo())
                .ordem(getOrdem())
                .build();

        if (includePrivilegio) {
            if (getVoluntarioList() != null)
                dto.setVoluntarioList(getVoluntarioList().stream().map(Voluntario::getDtoParcial).toList());
        }

        return dto;
    }


}
