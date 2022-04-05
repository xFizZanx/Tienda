package com.tienda.Service;

import com.tienda.entity.Persona;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrincipal implements UserDetails{
    private Persona persona;
    
    public UserPrincipal(Persona persona){
        this.persona = persona;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        
        this.persona.getPermissionList().forEach(p->{
            GrantedAuthority authority = new SimpleGrantedAuthority(p);
            authorities.add(authority);
        });
        
        this.persona.getRoleList().forEach(r->{
            GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_"+r);
            authorities.add(authority);
        });
        
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.persona.getPassword();
    }

    @Override
    public String getUsername() {
        return this.persona.getApellido1();
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
}
