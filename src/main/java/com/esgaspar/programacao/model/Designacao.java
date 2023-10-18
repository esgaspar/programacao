package com.esgaspar.programacao.model;

import com.esgaspar.programacao.model.dto.DesignacaoDto;
import com.esgaspar.programacao.util.Utils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "designacao")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class Designacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "data")
    LocalDate data;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "privilegio_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Privilegio privilegio;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "voluntario_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Voluntario voluntario;

    @JsonIgnore
    public DesignacaoDto getDto() {
        return DesignacaoDto.builder()
                .id(getId())
                .data(Utils.dateToString(getData()))
                .privilegio(getPrivilegio().getDto())
                .voluntario(getVoluntario().getDtoParcial())
                .build();
    }
}
