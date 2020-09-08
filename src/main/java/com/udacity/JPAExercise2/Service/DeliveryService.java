package com.udacity.JPAExercise2.Service;

import com.udacity.JPAExercise2.Entity.Delivery;
import com.udacity.JPAExercise2.Repository.DeliveryDTO;
import com.udacity.JPAExercise2.Repository.DeliveryRepository;
import com.udacity.JPAExercise2.Repository.RecipientAndPrice;
import com.udacity.JPAExercise2.Repository.RecipientAndPriceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.List;


@Service
public class DeliveryService {
    @Autowired
    DeliveryRepository deliveryRepository;
    @PersistenceContext
    EntityManager entityManager;

    //if we want to save a delivery object into database
    //1. we have to find out how many plant in the delivery
    //2. These plant need to setDelivery information, because it is ManyToOne so any plant need a delivery info
    //3. Make this delivery be managed by persistence context
    public Long save(Delivery delivery){
        delivery.getPlants().forEach(plant -> plant.setDelivery(delivery));
        deliveryRepository.persist(delivery);
        System.out.println(delivery.getId());
        return delivery.getId();
    }

    public List<DeliveryDTO> getDeliveryByName(String name){
        return deliveryRepository.getDeliveryByName(name);
    }

    public RecipientAndPriceDTO getRecipientAndPrice(Long deliveryid){
        return deliveryRepository.getRecipientAndPrice(deliveryid);
    }

    public RecipientAndPrice getBill(Long deliveryId){
        return deliveryRepository.getBill(deliveryId);
    }

}
