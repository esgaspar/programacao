package com.esgaspar.programacao.model;

import com.esgaspar.programacao.model.dto.VoluntarioDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "voluntario")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Voluntario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "descricao", nullable = false, length = 15)
    private String nome;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinTable(name = "voluntario_has_privilegio", joinColumns =
            {@JoinColumn(name = "voluntario_id")}, inverseJoinColumns =
            {@JoinColumn(name = "privilegio_id")})
    @OrderBy("ordem")
    private List<Privilegio> privilegios;

    @JsonIgnore
    public VoluntarioDto getDtoParcial() {
        return getDto(false);
    }

    @JsonIgnore
    public VoluntarioDto getDto() {
        return getDto(true);
    }

    @JsonIgnore
    private VoluntarioDto getDto(boolean includePrivilegio) {
        VoluntarioDto voluntarioDto = VoluntarioDto.builder()
                .id(getId())
                .nome(getNome())
                .build();

        if (includePrivilegio) {
            if (getPrivilegios() != null)
                voluntarioDto.setPrivilegioList(getPrivilegios().stream().map(Privilegio::getDtoParcial).toList());
        }

        return voluntarioDto;
    }
}
