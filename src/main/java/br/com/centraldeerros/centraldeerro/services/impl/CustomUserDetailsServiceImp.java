package br.com.centraldeerros.centraldeerro.services.impl;


import br.com.centraldeerros.centraldeerro.entities.Usuario;
import br.com.centraldeerros.centraldeerro.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class CustomUserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

        Optional<Usuario> user = usuarioRepository.findByUsername(name);
        if(user.isPresent()){
            return new UserRepositoryUserDetails(user.get());
        }
        throw new UsernameNotFoundException("User not found");

    }

    private final static class UserRepositoryUserDetails extends Usuario implements UserDetails {

        private static final long serialVersionUID = 1L;

        public UserRepositoryUserDetails(Usuario user){
            super(user);
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return null;
        }

        @Override
        public String getUsername() {
            return super.getUsername();
        }

        @Override
        public String getPassword() {
            return super.getPassword();
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
}