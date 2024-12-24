package com.example.ex1.Controller;

import com.example.ex1.Model.User;
import com.example.ex1.Model.UserRegisterDTO;
import com.example.ex1.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/home")
    public String home(){
        return "Hello";
    }


    @PostMapping("/user")
    public ResponseEntity<?> addUser(@RequestBody User user){
    return ResponseEntity.ok(userService.adduser(user));
    }
}
