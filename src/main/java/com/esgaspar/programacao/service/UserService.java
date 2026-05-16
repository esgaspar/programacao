package com.esgaspar.programacao.service;

import com.esgaspar.programacao.exception.ResourceNotFoundException;
import com.esgaspar.programacao.infra.security.SecurityUtils;
import com.esgaspar.programacao.mapper.UserMapper;
import com.esgaspar.programacao.model.dto.UserDto;
import com.esgaspar.programacao.repository.RoleRepository;
import com.esgaspar.programacao.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserDto find(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado: " + id));
    }

    public List<UserDto> list() {
        if (!SecurityUtils.isAdm()) return List.of();
        return userRepository.findAll().stream().map(userMapper::toDto).toList();
    }

    @Transactional
    public UserDto save(UserDto dto) {
        boolean isSelf = Objects.nonNull(dto.id()) && SecurityUtils.getUser().getId().equals(dto.id());

        if (!SecurityUtils.isAdm() || isSelf) {
            var entity = userMapper.toEntity(dto);
            entity.setPassword(passwordEncoder.encode(dto.password()));

            if (entity.getRoles() == null || entity.getRoles().isEmpty()) {
                roleRepository.findByName("user").ifPresent(role -> entity.setRoles(java.util.Set.of(role)));
            }

            return userMapper.toDto(userRepository.save(entity));
        }
        throw new AccessDeniedException("Operação não permitida");
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public UserDto findByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(userMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado: " + username));
    }
}
