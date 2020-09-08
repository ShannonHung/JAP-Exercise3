package com.udacity.JPAExercise2.Controller;

import com.udacity.JPAExercise2.Entity.Delivery;
import com.udacity.JPAExercise2.Repository.DeliveryDTO;
import com.udacity.JPAExercise2.Repository.RecipientAndPrice;
import com.udacity.JPAExercise2.Repository.RecipientAndPriceDTO;
import com.udacity.JPAExercise2.Service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("delivery")
public class DeliveryController {
    @Autowired
    DeliveryService deliveryService;

    @PostMapping
    public Long saveDelivery(@RequestBody Delivery delivery){
        return deliveryService.save(delivery);
    }

    @GetMapping("/name")
    public List<DeliveryDTO> getDeliveryDTOByName(@RequestParam String name){
        return deliveryService.getDeliveryByName(name);
    }

    @GetMapping("/deliveryid")
    public RecipientAndPriceDTO getRecipientAndPrice(@RequestParam Long deliveryid){

        return deliveryService.getRecipientAndPrice(deliveryid);
    }
    @GetMapping("/bill/{deliveryId}")
    public RecipientAndPrice getBill(@PathVariable Long deliveryId) {
        return deliveryService.getBill(deliveryId);
    }

}
