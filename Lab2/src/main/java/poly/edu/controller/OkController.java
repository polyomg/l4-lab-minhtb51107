package poly.edu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ctrl") // gắn prefix cho tất cả method
public class OkController {

    // OK 1: POST /ctrl/ok
    @PostMapping("/ok")
    public String m1(Model model) {
        model.addAttribute("msg", "Đã gọi m1()");
        return "ok"; // render ok.html
    }

    // OK 2: GET /ctrl/ok
    @GetMapping("/ok")
    public String m2(Model model) {
        model.addAttribute("msg", "Đã gọi m2()");
        return "ok";
    }

    // OK 3: POST /ctrl/ok?x
    @PostMapping(value = "/ok", params = "x")
    public String m3(Model model) {
        model.addAttribute("msg", "Đã gọi m3()");
        return "ok";
    }
}

