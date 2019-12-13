package com.carhouse.rest.controller;

import com.carhouse.model.User;
import com.carhouse.model.dto.AuthenticationRequestDto;
import com.carhouse.model.dto.AuthenticationResponseDto;
import com.carhouse.security.jwt.JwtTokenProvider;
import com.carhouse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Objects;

/**
 * The User controller.
 * Provide endpoints to login and register user
 *
 * @author Katuranau Maksimilyan
 */
@RestController
public class UserController {

    private static final String TOKEN_PREFIX = "Bearer_";

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    /**
     * Instantiates a new User controller.
     *
     * @param authenticationManager the authentication manager
     * @param jwtTokenProvider      the jwt token provider
     * @param userService           the user service
     */
    @Autowired
    public UserController(final AuthenticationManager authenticationManager, final JwtTokenProvider jwtTokenProvider,
                          final UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    /**
     * Login authentication response dto.
     *
     * @param requestDto the request dto
     * @return the authentication response dto
     */
    @PostMapping("/authentication/login")
    public AuthenticationResponseDto login(@RequestBody @Valid final AuthenticationRequestDto requestDto) {
        String userLogin = requestDto.getLogin();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLogin,
                requestDto.getPassword()));
        User user = userService.findByUserLogin(userLogin);
        String token = jwtTokenProvider.createToken(userLogin);
        AuthenticationResponseDto authenticationResponseDto = new AuthenticationResponseDto();
        authenticationResponseDto.setUserName(user.getUserName());
        authenticationResponseDto.setToken(TOKEN_PREFIX + token);
        return authenticationResponseDto;
    }

    /**
     * Register new user.
     * Check or login is unique.
     *
     * @param user the user
     * @return the authentication response dto
     */
    @PostMapping("/authentication/register")
    public AuthenticationResponseDto register(@RequestBody @Valid final User user) {
        String userLogin = user.getLogin();
        if (Objects.isNull(userService.findByUserLogin(userLogin))) {
            userService.addUser(user);
            String token = jwtTokenProvider.createToken(userLogin);
            AuthenticationResponseDto authenticationResponseDto = new AuthenticationResponseDto();
            authenticationResponseDto.setUserName(user.getUserName());
            authenticationResponseDto.setToken(TOKEN_PREFIX + token);
            return authenticationResponseDto;
        } else {
            throw new BadCredentialsException("User already exist");
        }
    }
}
