package br.com.compassuol.pb.challenge.ecommerce.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false, unique = true)
    private Integer productId;

    @Column(name = "product_name", nullable = false)
    @Size(min = 3)
    private String name;

    @Column(name = "product_price", nullable = false)
    private BigDecimal price;

    @Column(name = "product_description", nullable = false)
    @Size(min = 3)
    private String description;

    public Product(Integer productId, String name, BigDecimal price, String description) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public Product() {
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}