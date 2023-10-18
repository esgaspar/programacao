package com.esgaspar.programacao.model;

import com.esgaspar.programacao.model.dto.PrivilegioDto;
import com.esgaspar.programacao.model.dto.RoleDto;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public RoleDto getDto() {
        return RoleDto.builder()
                .id(getId())
                .name(getName())
                .build();
    }
}
