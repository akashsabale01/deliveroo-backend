package com.ecorp.deliveroo.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecorp.deliveroo.modal.enitity.Dish;

@Repository
public interface DishRepository extends JpaRepository<Dish, UUID>  {
	
	Optional<Dish> findByName(String name);

}
