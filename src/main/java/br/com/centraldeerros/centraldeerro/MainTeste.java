package br.com.centraldeerros.centraldeerro;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class MainTeste {
    public static void main(String[] args) {
        System.out.printf(new BCryptPasswordEncoder().encode("admin"));
    }
}
