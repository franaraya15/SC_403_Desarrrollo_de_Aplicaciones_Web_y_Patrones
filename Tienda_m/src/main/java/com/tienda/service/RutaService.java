package com.tienda.service;

import com.tienda.domain.Ruta;
import com.tienda.domain.Usuario;
import com.tienda.repository.RutaRepository;
import com.tienda.repository.UsuarioRepository;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service 
public class RutaService  {

    private final RutaRepository rutaRepository;

    public RutaService(RutaRepository rutaRepository) {
        this.rutaRepository = rutaRepository;
    }
    
    @Transactional(readOnly=true)
    public List<Ruta> getRutas() {
        return rutaRepository.findAllByOrderByRequiereRolAsc();
    }
    
    
}
