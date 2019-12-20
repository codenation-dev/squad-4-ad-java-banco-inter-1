package br.com.centraldeerro.services.implementations;


import br.com.centraldeerro.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsServiceImp implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public CustomUserDetailsServiceImp(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return usuarioRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Dados inválidos"));
    }

}