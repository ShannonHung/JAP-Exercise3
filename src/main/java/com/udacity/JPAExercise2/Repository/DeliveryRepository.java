package com.udacity.JPAExercise2.Repository;

import com.udacity.JPAExercise2.Entity.Delivery;
import com.udacity.JPAExercise2.Entity.Plant;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.*;

@Repository
@Transactional
public class DeliveryRepository {
    @PersistenceContext
    EntityManager entityManager;

    public void persist(Delivery delivery){
        entityManager.persist(delivery);
    }

    public Delivery find(Long id){
        return entityManager.find(Delivery.class, id);
    }

    public Delivery merge(Delivery delivery){
        return entityManager.merge(delivery);
    }

    public void delete(Long id){
        Delivery delivery = entityManager.find(Delivery.class, id);
        entityManager.remove(delivery);
    }

    public List<DeliveryDTO> getDeliveryByName(String name){
        TypedQuery<DeliveryDTO> query = entityManager.createNamedQuery("FIND_DELIVERY_BY_NAME", DeliveryDTO.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    public RecipientAndPriceDTO getRecipientAndPrice(Long deliveryid){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<RecipientAndPriceDTO> criteria = criteriaBuilder.createQuery(RecipientAndPriceDTO.class);
        //要取得Plant.class的任何內容都要透過root
        Root<Plant> root = criteria.from(Plant.class);
        //建構class instance裡面的參數要放name跟price,price要特別經過criteriaBuilder.sum
        criteria.select(criteriaBuilder.construct(
                RecipientAndPriceDTO.class,
                root.get("delivery").get("name"),
                criteriaBuilder.sum(root.get("price"))))
                .where(criteriaBuilder.equal(root.get("delivery").get("id"), deliveryid));
        return entityManager.createQuery(criteria).getSingleResult();

    }

    public RecipientAndPrice getBill(Long deliveryId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<RecipientAndPrice> query = cb.createQuery(RecipientAndPrice.class);
        Root<Plant> root = query.from(Plant.class);
        query.select(
                cb.construct(
                        RecipientAndPrice.class,
                        root.get("delivery").get("name"),
                        cb.sum(root.get("price"))))
                .where(cb.equal(root.get("delivery").get("id"), deliveryId));
        return entityManager.createQuery(query).getSingleResult();
    }
}
