package com.esgaspar.programacao.infra.security;

import com.esgaspar.programacao.model.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SecuryUtils {
    public static final boolean isAdm() {
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .filter(aut -> {
                    return aut.getAuthority().equalsIgnoreCase("admin");
                }).toList().isEmpty() == Boolean.FALSE;
    }

    public static final User getUser() {
        return ((Optional<User>) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).get();
    }

}