package com.esgaspar.programacao.model;

import com.esgaspar.programacao.model.dto.UserDto;
import com.esgaspar.programacao.model.dto.VoluntarioDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "users")
@Data
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> list = new ArrayList<SimpleGrantedAuthority>();

        if (getRoles() != null && getRoles().size() > 0) {

            for (Role role : getRoles()) {
                SimpleGrantedAuthority rol = new SimpleGrantedAuthority(role.getName());
                list.add(rol);
            }
        }

        return list;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @JsonIgnore
    public UserDto getDto() {
        UserDto dto = UserDto.builder()
                .id(getId())
                .name(getName())
                .username(getUsername())
                .email(getEmail())
                .build();

        if (getRoles() != null)
            dto.setRoles(getRoles().stream().map(Role::getDto).collect(Collectors.toSet()));

        return dto;
    }
}
