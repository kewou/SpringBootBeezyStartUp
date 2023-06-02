package org.example.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailService;

    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        return provider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // protocole de sécurité qui gère un token
                .authorizeRequests()
                .antMatchers("/logout").authenticated()
                .antMatchers("/").permitAll() // Tout le monde a accès à cette page
                //.antMatchers("/home").hasAnyAuthority("LOCATAIRE,ADMIN")
                .antMatchers("/admin").hasAuthority("ADMIN")
                .antMatchers("/proprio").hasAuthority("PROPRIO")
                .antMatchers("/locataire").hasAuthority("LOCATAIRE")
                .anyRequest().authenticated()    // toutes les requetes doivent etre authentifiées
                .and()
                .httpBasic()
                .and()
                .logout()
                .logoutUrl("/logout") // Spécifiez l'URL de déconnexion
                .logoutSuccessUrl("/login") // Redirigez vers une page de connexion après la déconnexion
                .invalidateHttpSession(true) // Invalidez la session HTTP après la déconnexion
                .deleteCookies("JSESSIONID");
        ;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/configuration/ui", "/swagger-resources", "/configuration/ui",
                        "/swagger-resources/**", "/configuration/security", "/api-docs/swagger-config", "/swagger-ui/**", "/webjars/**")
                .antMatchers("/users/*")
                .antMatchers("/api-docs")
                .antMatchers("/actuator");
    }
}
