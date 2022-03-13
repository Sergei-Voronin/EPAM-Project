package com.example.Forex.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "currencies")
@SequenceGenerator(name = "userIdGenerator", initialValue = 1, allocationSize = 1)
public class Valute implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userIdGenerator")
    private Long id;
    @Column(name = "numCode")
    private int numCode;
    @Column(name = "charCode")
    private String charCode;
    @Column(name = "name")
    private String name;
    @Column(name = "value")
    private BigDecimal value;

    public Valute() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Valute(int numCode, String charCode, String name, BigDecimal value) {
        this.numCode = numCode;
        this.charCode = charCode;
        this.name = name;
        this.value = value;
    }

    public int getNumCode() {
        return numCode;
    }

    public void setNumCode(int numCode) {
        this.numCode = numCode;
    }

    public String getCharCode() {
        return charCode;
    }

    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Valute{" +
                "numCode=" + numCode +
                ", charCode='" + charCode + '\'' +
                ", name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
