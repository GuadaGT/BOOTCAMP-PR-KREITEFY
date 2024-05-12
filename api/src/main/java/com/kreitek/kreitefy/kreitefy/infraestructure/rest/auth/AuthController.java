package com.kreitek.kreitefy.kreitefy.infraestructure.rest.auth;

import com.kreitek.kreitefy.kreitefy.application.dto.LoginDto;
import com.kreitek.kreitefy.kreitefy.application.dto.UserSimpleDto;
import com.kreitek.kreitefy.kreitefy.application.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthController(AuthService authService, JwtService jwtService,
                          PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.authService = authService;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }
    @PostMapping(value = "/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginDto loginDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(), loginDto.getPassword()));
        UserSimpleDto user = authService.getUser(loginDto.getUsername()).orElseThrow();
        String token = jwtService.generateToken(user);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping(value = "/register")
    public ResponseEntity<AuthResponse> register(@RequestBody UserSimpleDto userSimpleDto) {
        userSimpleDto.setPassword(passwordEncoder.encode(userSimpleDto.getPassword()));
        UserSimpleDto userDtoRegistered = authService.register(userSimpleDto);
        String token = jwtService.generateToken(userDtoRegistered);
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
