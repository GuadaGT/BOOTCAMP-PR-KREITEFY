package com.kreitek.kreitefy.kreitefy.infraestructure.utils;

public class ValidationUtils {
    public static boolean isValidEmail(String email) {
        return email != null && email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
    }
}
