package com.example.railway.controller;

import com.example.railway.domain.dto.BaseResponse;
import com.example.railway.domain.dto.request.UserRequest;
import com.example.railway.domain.dto.response.UserResponse;
import com.example.railway.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final UserService service;

    @GetMapping("/register")
    public String registerPage() {
        return "index";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UserRequest request, Model model) {

        BaseResponse<UserResponse> response = service.create(request);

        if (response.getStatus() == 401) {
            model.addAttribute("message", response.getMessage());
            return "index";
        }

        if (response.getData().getRole().equals("ADMIN")) {
            model.addAttribute("message", response.getMessage());
            return "AdminWindow";
        } else if (response.getData().getRole().equals("USER")) {
            model.addAttribute("message", response.getMessage());
            return "UserWindow";
        }
        return "index";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "index";
    }


    @PostMapping("/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        HttpSession session, Model model) {

        BaseResponse<UserResponse> login = service.login(email, password);

        if (login.getStatus() == 401) {
            model.addAttribute("message", login.getMessage());
            return "index";
        }

        session.setAttribute("id", login.getData().getId());
        model.addAttribute("id", login.getData().getId());

        if (login.getData().getRole().equals("ADMIN")) {
            model.addAttribute("message", login.getMessage());
            return "AdminWindow";
        } else if (login.getData().getRole().equals("USER")) {
            model.addAttribute("message", login.getMessage());
            return "UserWindow";
        }
        model.addAttribute("message", "User role not found");
        return "index";
    }
}
