package com.tienda;

import static ch.qos.logback.classic.spi.LoggingEventVO.build;
import com.tienda.domain.Ruta;
import com.tienda.service.RutaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration

public class SecurityConfig {
    
  /*  //se establecen las rutas del sitio accesibles segun el rol del usuario
    //la próxima semana se supera esto...
    //A continuacion van las rutas que TODOS pueden acceder en el sistema

    public static final String[] PUBLIC_URLS = {"/", "/index",
        "/fav/**", "/webjars/**", "/js/**", "/login", "/acceso_denegado"};

    //a continuacion van las rutas que el usuario puede acceder
    public static final String[] USUARIO_URLS = {"/facturar/carrito"};

    //a continuacion las rutas que los vendedores pueden acceder en el sistema
    public static final String[] VENDEDOR_URLS = {"/categoria/listado",
        "/producto/listado", "/consultas/**"};
    //a continuacion las rutas que los administradores pueden acceder en el sistema
    public static final String[] ADMIN_URLS = {"/categoria/**",
        "/producto/**", "/consultas/**", "/usuario/**"};
*/
    //A continuación se define el método que configura la autorización en el sistema
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
            @Lazy RutaService rutaService) throws Exception {
        //se recuperan las rutas de la tabla rutas
        var rutas = rutaService.getRutas();
        
        //Se establece que perfil tiene acceso a que recurso...
        http.authorizeHttpRequests(request -> {
            for (Ruta ruta : rutas) {
                if (ruta.isRequiereRol()) {
                    request.requestMatchers(ruta.getRuta()).hasRole(ruta.getRol().getRol());
                } else {
                    request.requestMatchers(ruta.getRuta()).permitAll();
                }
            }
            request.anyRequest().authenticated();
                
        });

        //Se establece cómo se hace el LOGIN
        http.formLogin(form -> form
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/", true)
                .failureUrl("/login?error=true")
                .permitAll()
        );

        //Se establece cómo se hace el "logout"
        http.logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout=true")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
        );

        //Qué se hace con errores de acceso?
        http.exceptionHandling(ex -> ex
                .accessDeniedPage("/acceso_denegado")
        );

        //Que hacemos con sesiones múltiples
        http.sessionManagement(ses -> ses
                .maximumSessions(1)
                .maxSessionsPreventsLogin(false)
        );

        return http.build();
    }
    //Se define la forma de encriptar las claves

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }
    //esto es para identificar usuarios
    @Autowired
    public void configurerGlobal(AuthenticationManagerBuilder build,
           @Lazy PasswordEncoder passwordEncoder,
           @Lazy UserDetailsService userDetailsService) throws Exception {
      build.userDetailsService(userDetailsService).passwordEncoder (passwordEncoder);  
    }
    
    /* queda como conocimiento
// se define los 3 usuarios "en memoria" la proxima semana esto se borra

    @Bean
    public UserDetailsService users(PasswordEncoder passwordEncoder
    ) {
//se define juan como ADMIN
        UserDetails user1 = User.builder().username("juan")
                .password(passwordEncoder.encode("123"))
                .roles("ADMIN")
                .build();

//se define rebeca como VENDEDOR
        UserDetails user2 = User.builder().username("rebeca")
                .password(passwordEncoder.encode("456"))
                .roles("VENDEDOR")
                .build();

//se define pedro como USUARIO
        UserDetails user3 = User.builder().username("pedro")
                .password(passwordEncoder.encode("789"))
                .roles("USUARIO")
                .build();
//se crean los 3 usuarios en memoria
        return new InMemoryUserDetailsManager(user1, user2, user3);
    }
*/

}
