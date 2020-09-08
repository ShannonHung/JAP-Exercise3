package com.udacity.JPAExercise2.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name="plant")
@Inheritance(strategy = InheritanceType.JOINED)
public class Plant {
    @Id
    @GeneratedValue
    Long id;

    @JsonView(Views.Public.class)
    @Nationalized
    String name;

    @JsonView(Views.Public.class)
    @Column(precision = 12, scale = 4)
    BigDecimal price;

    //do not retrieve delivery if we don't need it
    @ManyToOne(fetch = FetchType.LAZY) //many plants can belong to one delivery
    @JoinColumn(name="delivery_id") //map the join column in the plant table
    private Delivery delivery; //delivery.class裡面會透過mappedby來跟這裡連結

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }
}
