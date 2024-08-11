package com.ecorp.deliveroo.modal.enitity;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Featured {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	private String name;

	private String shortDescription;

	@ManyToMany
	@JoinTable(name = "featured_restaurants", joinColumns = @JoinColumn(name = "featured_id"), inverseJoinColumns = @JoinColumn(name = "restaurant_id"))
	private List<Restaurant> restaurants;

}