package com.example.demo.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.service.CookieService;
import com.example.demo.service.ParamService;
import com.example.demo.service.SessionService;

@Controller
public class AccountController {
    @Autowired
    ParamService paramService;

    @Autowired
    CookieService cookieService;

    @Autowired
    SessionService sessionService;

    @GetMapping("/account/login")
    public String login1(Model model) {
        String username = cookieService.getValue("user");
        model.addAttribute("username", username);
        return "/account/login";
    }


    @PostMapping("/account/login")
    public String login2() {
        String username = paramService.getString("username", "");
        String password = paramService.getString("password", "");
        boolean remember = paramService.getBoolean("remember", false);

        if ("poly".equals(username) && "123".equals(password)) {
            sessionService.set("username", username);
            if (remember) {
                cookieService.add("user", username, 240); // 10 ngày
            } else {
                cookieService.remove("user");
            }
            return "redirect:/item/index";
        }

        return "/account/login";
    }
    
    @GetMapping("/account/register")
    public String registerForm() {
        return "/account/register";
    }
    
    @PostMapping("/account/register")
    public String registerSubmit(
            @RequestParam("photo") MultipartFile file,
            Model model) {

        // 1️⃣ Đọc các tham số
        String username = paramService.getString("username", "");
        String password = paramService.getString("password", "");

        // 2️⃣ Lưu file ảnh (ví dụ: vào thư mục /images)
        File savedFile = paramService.save(file, "/images");

        // 3️⃣ Xử lý logic đăng ký (demo thôi)
        model.addAttribute("username", username);
        model.addAttribute("password", password);
        model.addAttribute("photoPath", "/images/" + (savedFile != null ? savedFile.getName() : "no-image"));

        return "/account/register-success";
    }
}

