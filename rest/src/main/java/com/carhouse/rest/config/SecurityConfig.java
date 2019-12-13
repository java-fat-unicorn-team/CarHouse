package com.carhouse.rest.config;

import com.carhouse.security.jwt.JwtConfigurer;
import com.carhouse.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * The type Security config.
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

    private static final String PERMITTED_ENDPOINT = "/carSale/**";
    private static final String PERMITTED_ENDPOINT_CAR_CHARACTERISTICS = "/carCharacteristics";
    private static final String AUTHENTICATION_ENDPOINT = "/authentication/**";

    /**
     * Instantiates a new Security config.
     *
     * @param jwtTokenProvider the jwt token provider
     */
    @Autowired
    public SecurityConfig(final JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(PERMITTED_ENDPOINT).permitAll()
                .antMatchers(PERMITTED_ENDPOINT_CAR_CHARACTERISTICS).permitAll()
                .antMatchers(AUTHENTICATION_ENDPOINT).permitAll()
                .anyRequest().authenticated()
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));
    }
}
