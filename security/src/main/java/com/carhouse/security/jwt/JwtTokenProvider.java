package com.carhouse.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

/**
 * The type Jwt token provider.
 */
@Component
public class JwtTokenProvider {

    @Value("${jwt.token.secret}")
    private String SECRET;

    @Value("${jwt.token.expired}")
    private long VALIDATION_MILLISECONDS;

    @Value("${token.prefix.size}")
    private int TOKEN_PREFIX_SIZE;

    @Autowired
    @Qualifier(value = "customUserDetailsService")
    private UserDetailsService userDetailsService;

    /**
     * Password encoder b crypt password encoder.
     *
     * @return the b crypt password encoder
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Init.
     */
    @PostConstruct
    protected void init() {
        SECRET = Base64.getEncoder().encodeToString(SECRET.getBytes());
    }

    /**
     * Create token string.
     *
     * @param userLogin the user login
     * @return the string
     */
    public String createToken(final String userLogin) {

        Claims claims = Jwts.claims().setSubject(userLogin);

        Date now = new Date();
        Date validity = new Date(now.getTime() + VALIDATION_MILLISECONDS);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    /**
     * Gets authentication.
     *
     * @param token the token
     * @return the authentication
     */
    public Authentication getAuthentication(final String token) {
        try {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUserLogin(token));
            return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
        } catch (UsernameNotFoundException e) {
            return null;
        }
    }

    /**
     * Gets user login.
     *
     * @param token the token
     * @return the user login
     */
    public String getUserLogin(final String token) {
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody().getSubject();
    }

    /**
     * Resolve token string.
     *
     * @param req the req
     * @return the string
     */
    public String resolveToken(final HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer_")) {
            return bearerToken.substring(TOKEN_PREFIX_SIZE);
        }
        return null;
    }

    /**
     * Validate token boolean.
     *
     * @param token the token
     * @return the boolean
     */
    public boolean validateToken(final String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);

            if (claims.getBody().getExpiration().before(new Date())) {
                return false;
            }

            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
