package com.ecorp.deliveroo.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecorp.deliveroo.modal.enitity.Featured;

@Repository
public interface FeaturedRepository extends JpaRepository<Featured, UUID> {

	Optional<Featured> findByName(String name);
}
