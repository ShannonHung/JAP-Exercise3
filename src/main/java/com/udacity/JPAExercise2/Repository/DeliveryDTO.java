package com.udacity.JPAExercise2.Repository;

import com.udacity.JPAExercise2.Entity.Plant;

import java.time.LocalDateTime;
import java.util.*;

public class DeliveryDTO {
    private String name;
    private String address;
    private LocalDateTime deliveryTime;
//    private List<Plant> plants;

    public DeliveryDTO(){}
    public DeliveryDTO(String name, String address, LocalDateTime deliveryTime){
        this.name = name;
        this.address = address;
        this.deliveryTime = deliveryTime;
//        this.plants = plants;
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

//    public List<Plant> getPlants() {
//        return plants;
//    }
//
//    public void setPlants(List<Plant> plants) {
//        this.plants = plants;
//    }
}
