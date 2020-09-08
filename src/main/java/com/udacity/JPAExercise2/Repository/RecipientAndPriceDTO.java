package com.udacity.JPAExercise2.Repository;

import java.math.BigDecimal;

public class RecipientAndPriceDTO {
    private String name;
    private BigDecimal price;

    public RecipientAndPriceDTO(){}
    public RecipientAndPriceDTO(String name, BigDecimal price){
        this.name=name;
        this.price=price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
