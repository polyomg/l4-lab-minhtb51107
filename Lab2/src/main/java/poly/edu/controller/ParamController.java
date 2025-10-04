package poly.edu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import poly.edu.model.Product; // Import the Product model

@Controller
public class ParamController {

    @RequestMapping("/param/form")
    public String form(Model model) { // Add Model to the method
        model.addAttribute("product", new Product()); // Add a new Product object
        return "form";
    }

    @RequestMapping(value = "/param/save/{x}", method = RequestMethod.POST)
    public String save(@PathVariable("x") String x, @RequestParam("y") String y, Model model) {
        model.addAttribute("x", x);
        model.addAttribute("y", y);
        return "form";
    }
}