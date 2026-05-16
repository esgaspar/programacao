package com.esgaspar.programacao.model.dto;

import java.util.List;

public record PrivilegioDto(
        Long id,
        String descricao,
        Integer ordem,
        String codigo,
        Boolean checked,
        List<VoluntarioDto> voluntarioList
) {
    public PrivilegioDto {
        if (checked == null) checked = true;
    }
}
