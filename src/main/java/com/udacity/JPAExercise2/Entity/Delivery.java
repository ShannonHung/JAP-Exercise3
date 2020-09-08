package com.udacity.JPAExercise2.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.udacity.JPAExercise2.Repository.DeliveryDTO;
import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

//i don't know why but not work
@NamedQueries({
            //這裡個query不能使用List<plant>因為他們是透過joined的方式把兩個table連接起來，實際上delivery並沒有plants
            @NamedQuery(name = "FIND_DELIVERY_BY_NAME",
                query = "SELECT new com.udacity.JPAExercise2.Repository.DeliveryDTO(" +
                        "d.name, d.address, d.deliveryTime)" +
                        "from Delivery d where d.name = :name"),
            @NamedQuery(name = "FIND_BY_NAME",
                    query = "select d from Delivery d where d.name = :name")
            //query裡面的from entity一定要跟class名稱一樣因為他是jpql是透過object去資料庫找
}
)
@Entity
public class Delivery {
    @Id
    @GeneratedValue
    private Long id;

    @Nationalized
    private String name;

    @Column(name="address_full", length = 500)
    private String address;
    private LocalDateTime deliveryTime;

    @Type(type="yes_no")
    private Boolean complete;

    //added CascadedType.REMOVE to automatically clear any associated plants when removed
//    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "delivery", cascade = CascadeType.ALL)
    private List<Plant> plants;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(LocalDateTime deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Boolean getComplete() {
        return complete;
    }

    public void setComplete(Boolean complete) {
        this.complete = complete;
    }

    public List<Plant> getPlants() {
        return plants;
    }

    public void setPlants(List<Plant> plants) {
        this.plants = plants;
    }

    @Override
    public String toString() {

        return "Delivey: \n" +
                "name:" + this.name + "\n" +
                "address:" + this.address + "\n" +
                "LocalDateTime:" + this.deliveryTime + "\n" +
                "PlantSize:" + plants.size();

    }
}
