package com.kreitek.kreitefy.kreitefy.infraestructure.rest.auth;

public class AuthResponse {
    private String token;
    private String message;

    public AuthResponse() {
    }

    public AuthResponse(String token, String message) {
        this.token = token;
        this.message = message;
    }

    public AuthResponse(String token) {
        this.token = token;

    }

    public String getToken() {
        return token;
    }

    public String getMessage() {
        return message;
    }
}
