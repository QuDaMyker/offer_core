package com.builtlab.offer_services.repository;

import com.builtlab.offer_services.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface OfferRepository extends MongoRepository<Offer, String> {
    Page<Offer> findAll(Pageable pageable);
}
