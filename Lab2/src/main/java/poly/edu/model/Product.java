package poly.edu.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor  // Lombok sẽ tự tạo constructor không có tham số
@AllArgsConstructor // Lombok sẽ tự tạo constructor có đầy đủ tham số
public class Product {
	private String name;
    private Double price;

//    public Product() {
//    }
//
//    public Product(String name, Double price) {
//        this.name = name;
//        this.price = price;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public Double getPrice() {
//        return price;
//    }
//
//    public void setPrice(Double price) {
//        this.price = price;
//    }
}
