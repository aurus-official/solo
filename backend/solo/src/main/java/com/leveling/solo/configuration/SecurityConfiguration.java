package com.leveling.solo.configuration;

import com.leveling.solo.handler.CustomLogoutSuccessHandler;
import com.leveling.solo.handler.LoginAuthenticationEntryPoint;
import com.leveling.solo.player.PlayerValidationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfiguration {

    private final PlayerValidationService playerValidationService;
    private final LoginAuthenticationEntryPoint loginAuthenticationEntryPoint;
    private final CustomLogoutSuccessHandler logoutHandler;

    @Autowired
    SecurityConfiguration(PlayerValidationService playerValidationService,
            LoginAuthenticationEntryPoint loginAuthenticationEntryPoint, CustomLogoutSuccessHandler logoutHandler) {
        this.playerValidationService = playerValidationService;
        this.loginAuthenticationEntryPoint = loginAuthenticationEntryPoint;
        this.logoutHandler = logoutHandler;
    }

    @Bean
    BCryptPasswordEncoder getBCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    HttpSessionSecurityContextRepository getHttpSessionSecurityContextRepository() {
        return new HttpSessionSecurityContextRepository();
    }

    @Bean
    SecurityFilterChain setupSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/POST", "/v1/auth/register").permitAll()
                        .anyRequest().authenticated())
                .authenticationProvider(setupAuthenticationProvider())
                .securityContext(securityContextConfigurer -> securityContextConfigurer
                        .securityContextRepository(getHttpSessionSecurityContextRepository()))
                .httpBasic(httpSecurityHttpBasicConfigurer -> httpSecurityHttpBasicConfigurer
                        .authenticationEntryPoint(loginAuthenticationEntryPoint)
                        .securityContextRepository(getHttpSessionSecurityContextRepository()))
                .logout(logoutConfigurer -> logoutConfigurer.deleteCookies("SESSION")
                        .invalidateHttpSession(true)
                        .logoutUrl("/v1/auth/logout")
                        .logoutSuccessHandler(logoutHandler));
        return http.build();
    }

    AuthenticationProvider setupAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(getBCryptPasswordEncoder());
        daoAuthenticationProvider.setUserDetailsService(playerValidationService);
        return daoAuthenticationProvider;
    }
}
