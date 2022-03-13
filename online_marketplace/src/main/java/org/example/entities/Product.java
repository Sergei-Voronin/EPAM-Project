package org.example.entities;

import lombok.*;
import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(name = "products")
@SequenceGenerator(name = "productIdGenerator", initialValue = 1, allocationSize = 1)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "productIdGenerator")
    private Integer productId;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private Double price;
    @Column(name = "auction_timer")
    private Timestamp auctionTimer;

    public Product() {}

    public Product(Integer userId, String description, Double price, Timestamp auctionTimer) {
        this.userId = userId;
        this.description = description;
        this.price = price;
        this.auctionTimer = auctionTimer;
    }

    public Product(Integer productId, int userId, String description, Double price, Timestamp auctionTimer) {
        this.productId = productId;
        this.userId = userId;
        this.description = description;
        this.price = price;
        this.auctionTimer = auctionTimer;
    }

    @Override
    public String toString() {
        return "Product{" +
                "product_id=" + productId +
                ", user_id=" + userId +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", auction_timer=" + auctionTimer +
                '}';
    }
}
