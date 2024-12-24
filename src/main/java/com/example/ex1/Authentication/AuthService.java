package com.example.ex1.Authentication;

import com.example.ex1.Model.User;
import com.example.ex1.Repo.UserRepo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepo userRepo;
    private final BCryptPasswordEncoder encoder;
    private final JWTUtil jwtUtil;

    public AuthService(UserRepo userRepo, JWTUtil jwtUtil) {
        this.userRepo = userRepo;
        this.encoder = new BCryptPasswordEncoder(12);
        this.jwtUtil = jwtUtil;
    }

    public String authenticate(String username, String password) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Invalid username or password"));

        if (!encoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("Invalid username or password");
        }

        return jwtUtil.generateToken(user.getUsername(), user.getRole());
    }
}
