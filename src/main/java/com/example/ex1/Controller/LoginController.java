package com.example.ex1.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @GetMapping("/auth/google-login")
    public String googleLogin(){
        return "redirect:/oauth2/authorization/google";
    }

    @GetMapping("/auth/login")
    public String login() {
        return "<h1>Login</h1><a href='/oauth2/authorization/google'>Login with Google</a><br>"
                + "<a href='/oauth2/authorization/github'>Login with GitHub</a>";
    }


}
