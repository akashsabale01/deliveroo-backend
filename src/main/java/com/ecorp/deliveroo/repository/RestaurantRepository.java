package com.ecorp.deliveroo.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecorp.deliveroo.modal.enitity.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, UUID> {

	Optional<Restaurant> findByName(String name);
}
