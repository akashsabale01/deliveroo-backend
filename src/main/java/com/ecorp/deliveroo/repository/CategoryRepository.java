package com.ecorp.deliveroo.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecorp.deliveroo.modal.enitity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {

	Optional<Category> findByName(String name);
}
