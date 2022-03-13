package org.example.entities;

import lombok.*;
import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "bets")
@SequenceGenerator(name = "betIdGenerator", initialValue = 1, allocationSize = 1)
public class Bet {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "betIdGenerator")
    private Integer betId;
    @Column(name = "product_id")
    private Integer productId;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "bet")
    private Double rateValue;

    public Bet() {}

    public Bet(Integer productId, Integer userId, Double rateValue) {
        this.productId = productId;
        this.userId = userId;
        this.rateValue = rateValue;
    }

    public Bet(Integer betId, Integer productId, Integer userId, Double rateValue) {
        this.betId = betId;
        this.productId = productId;
        this.userId = userId;
        this.rateValue = rateValue;
    }

    @Override
    public String toString() {
        return "Bet{" +
                "bet_id=" + betId +
                ", product_id=" + productId +
                ", user_id=" + userId +
                ", bet=" + rateValue +
                '}';
    }
}
