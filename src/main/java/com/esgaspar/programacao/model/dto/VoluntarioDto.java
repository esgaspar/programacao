package com.esgaspar.programacao.model.dto;

import java.util.List;

public record VoluntarioDto(Long id, String nome, List<PrivilegioDto> privilegioList) {}
