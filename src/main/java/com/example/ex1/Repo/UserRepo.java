package com.example.ex1.Repo;

import com.example.ex1.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository< User, Long> {
    Optional<User> findByUsername(String username);
}
