package org.kulanos.pp_3_1_3_bootsecurity.security;

import org.kulanos.pp_3_1_3_bootsecurity.service.MyUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class MySecurityConfig {


    @Bean
    public UserDetailsService userDetailsService() throws Exception {

        System.out.println(passwordEncoder().encode("123"));

        return new MyUserDetailsService();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/").hasAnyRole("USER", "ADMIN")
                                .requestMatchers("/**").hasRole("ADMIN")
                                .anyRequest().authenticated()
                )
                .formLogin(Customizer.withDefaults()
                )
                .logout(logout ->
                        logout.permitAll()
                );
        return http.build();

    }



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }





    @Bean
    public AuthenticationProvider authenticationProvider() throws Exception {
       DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
       authenticationProvider.setUserDetailsService(userDetailsService());
       authenticationProvider.setPasswordEncoder(passwordEncoder());
       return authenticationProvider;
    }



}
