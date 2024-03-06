package com.example.securitypart7.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)//tell us method controller
// (  @PreAuthorize("hasAuthority('read')")//security)@PostAuthorize @PreFilter @PostFilter
//@Secured
//@RolsAllowed
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.httpBasic()
                .and()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .build();
    }
    @Bean
    public UserDetailsService userDetailsService(){
        var usd = new InMemoryUserDetailsManager();

        var u1 = User.withUsername("bill")
                .password(passwordEncoder().encode("12345"))
                .authorities("read")
                .build();

        var u2 = User.withUsername("john")
                .password(passwordEncoder().encode("12345"))
                .authorities("write")
                .build();
        usd.createUser(u1);
        usd.createUser(u2);

        return usd;

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();//noOpPasswordEncoder --->marked deprecated
        // (only use it in demonstration)
    }
}
