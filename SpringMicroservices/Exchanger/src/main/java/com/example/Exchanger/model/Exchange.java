package com.example.Exchanger.model;

import java.math.BigDecimal;

public class Exchange {
    private String from;
    private String fromName;
    private String to;
    private String toName;
    private BigDecimal conversionFactor;
    private BigDecimal quantity;
    private BigDecimal totalCost;

    public Exchange() {
    }

    public Exchange(String from, String to, BigDecimal conversionFactor, BigDecimal quantity, BigDecimal totalCost) {
        this.from = from;
        this.to = to;
        this.conversionFactor = conversionFactor;
        this.quantity = quantity;
        this.totalCost = totalCost;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public BigDecimal getConversionFactor() {
        return conversionFactor;
    }

    public void setConversionFactor(BigDecimal conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }


    @Override
    public String toString() {
        return "Exchange{" +
                "from='" + from + '\'' +
                ", fromName='" + fromName + '\'' +
                ", to='" + to + '\'' +
                ", toName='" + toName + '\'' +
                ", conversionFactor=" + conversionFactor +
                ", quantity=" + quantity +
                ", totalCost=" + totalCost +
                '}';
    }
}
