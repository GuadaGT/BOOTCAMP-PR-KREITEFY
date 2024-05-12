package com.kreitek.kreitefy.kreitefy.infraestructure.rest.auth;
import com.kreitek.kreitefy.kreitefy.application.dto.UserSimpleDto;
import com.kreitek.kreitefy.kreitefy.domain.type.RolType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class AuthUserDetails implements UserDetails {
    private final String userName;
    private final String userPassword;
    private final RolType rol;

    public AuthUserDetails(UserSimpleDto userSimpleDtoDto) {
        this.userName = userSimpleDtoDto.getUsername();
        this.userPassword = userSimpleDtoDto.getPassword();
        this.rol = userSimpleDtoDto.getRol();
    }

    @Override public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(rol.name()));
    }

    @Override public String getPassword() {
        return userPassword;
    }

    @Override public String getUsername() {
        return userName;
    }

    @Override public boolean isAccountNonExpired() {
        return true;
    }

    @Override public boolean isAccountNonLocked() {
        return true;
    }

    @Override public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override public boolean isEnabled() {
        return true;
    }

}
