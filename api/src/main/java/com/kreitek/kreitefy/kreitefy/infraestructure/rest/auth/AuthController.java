package com.kreitek.kreitefy.kreitefy.infraestructure.rest.auth;

import com.kreitek.kreitefy.kreitefy.application.dto.LoginDto;
import com.kreitek.kreitefy.kreitefy.application.dto.UserSimpleDto;
import com.kreitek.kreitefy.kreitefy.application.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import static com.kreitek.kreitefy.kreitefy.infraestructure.utils.ValidationUtils.isValidEmail;

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
        if (userSimpleDto.getUsername() == null || userSimpleDto.getUsername().isEmpty()) {
            return ResponseEntity.badRequest().body(new AuthResponse(null,"Username is required."));
        }
        if (userSimpleDto.getFirstName() == null || userSimpleDto.getFirstName().isEmpty()) {
            return ResponseEntity.badRequest().body(new AuthResponse(null,"First name is required."));
        }
        if (userSimpleDto.getLastName() == null || userSimpleDto.getLastName().isEmpty()) {
            return ResponseEntity.badRequest().body(new AuthResponse(null,"Last name is required."));
        }
        if (userSimpleDto.getPassword() == null || userSimpleDto.getPassword().isEmpty()) {
            return ResponseEntity.badRequest().body(new AuthResponse(null,"Password is required."));
        }
        if (userSimpleDto.getEmail() == null || !isValidEmail(userSimpleDto.getEmail())) {
            return ResponseEntity.badRequest().body(new AuthResponse(null,"Invalid email format."));
        }

        userSimpleDto.setPassword(passwordEncoder.encode(userSimpleDto.getPassword()));
        UserSimpleDto userDtoRegistered = authService.register(userSimpleDto);
        String token = jwtService.generateToken(userDtoRegistered);
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
