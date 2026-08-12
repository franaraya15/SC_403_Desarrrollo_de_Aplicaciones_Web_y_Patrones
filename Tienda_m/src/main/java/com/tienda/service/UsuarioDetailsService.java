package com.tienda.service;

import com.tienda.domain.Usuario;
import com.tienda.repository.UsuarioRepository;
import jakarta.servlet.http.HttpSession;
import java.util.stream.Collectors;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service ("userDetailsService")
public class UsuarioDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final HttpSession session;

    public UsuarioDetailsService(UsuarioRepository usuarioRepository, HttpSession session) {
        this.usuarioRepository = usuarioRepository;
        this.session = session;
    }
    
    //este método busca en la tabla usuario el registro con el username del login
    //si lo encuentra guarda la foto y crea el usuario...
    @Override
    @Transactional (readOnly=true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    //se busca el usuario con el username...
    Usuario usuario = usuarioRepository.findByUsernameAndActivoTrue(username)
            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"+username));
    
    //si estamos acá es que lo encontró...
    session.removeAttribute("imagenUsuario");
    session.setAttribute("imagenUsuario", usuario.getRutaImagen());
    
    //se cargan los roles del usaurio gracias a la relacion de miuchos a muchos y se pone como roles de
    var roles = usuario.getRoles().stream().map(rol -> new SimpleGrantedAuthority("ROLE_"+rol.getRol()))
            .collect(Collectors.toSet());
    
    //se devuelve el usuario como un User del sistema
    return new User(usuario.getUsername(),usuario.getPassword(),roles);
    }
    
}
