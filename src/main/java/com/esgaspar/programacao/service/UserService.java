package com.esgaspar.programacao.service;

import com.esgaspar.programacao.infra.security.SecuryUtils;
import com.esgaspar.programacao.model.User;
import com.esgaspar.programacao.model.dto.UserDto;
import com.esgaspar.programacao.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.text.Collator;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class UserService {
    @Autowired
    UserRepository repository;

    @Autowired
    AuthService authService;

    public UserDto find(Long id) {
        Optional<User> opt = repository.findById(id);

        User entity = opt.orElse(User.builder().build());
        return entity.getDto();
    }

    public List<UserDto> list() {
        List<User> list = new ArrayList<>();
        if (SecuryUtils.isAdm()) {
            list = repository.findAll();
        }
        return list.stream().map(User::getDto).toList();
    }

    @Transactional
    public UserDto save(UserDto dto) {

        if (SecuryUtils.isAdm() || Objects.nonNull(dto.getId())
                && SecuryUtils.getUser().getId().compareTo(dto.getId()) == 0) {
            dto.setPassword(new BCryptPasswordEncoder().encode(dto.getPassword()));
            return repository.save(dto.getEntity()).getDto();
        }
        throw new AccessDeniedException("Operação não permitida");
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public UserDto findByUsername(String username) {
        Optional<User> opt = repository.findByUsername(username);
        User entity = opt.orElse(User.builder().build());
        return entity.getDto();
    }
}
