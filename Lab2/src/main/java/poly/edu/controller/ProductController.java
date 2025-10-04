package poly.edu.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import poly.edu.model.Product;

@Controller
public class ProductController {

    // lưu danh sách sản phẩm khác (ban đầu có 2 sản phẩm)
    private List<Product> items = new ArrayList<>(
        Arrays.asList(
            new Product("A", 1.0),
            new Product("B", 12.0)
        )
    );

    @GetMapping("/product/form")
    public String form(Model model) {
        Product p = new Product("iPhone 30", 5000.0);
        model.addAttribute("product", p);
        return "product/form";
    }

    @PostMapping("/product/save")
    public String save(@ModelAttribute("product") Product p, Model model) {
        // cập nhật sản phẩm hiển thị
        model.addAttribute("product", p);

        // thêm vào danh sách items
        items.add(p);

        return "product/form"; 
        // hoặc redirect:/product/form nếu muốn tránh resubmit
    }

    @ModelAttribute("items") // ?3
    public List<Product> getItems() {
        return items; // luôn trả về danh sách hiện tại
    }
}
