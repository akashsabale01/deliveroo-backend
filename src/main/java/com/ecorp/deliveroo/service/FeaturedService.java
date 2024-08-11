package com.ecorp.deliveroo.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecorp.deliveroo.exceptions.ResourceNotFoundException;
import com.ecorp.deliveroo.modal.enitity.Featured;
import com.ecorp.deliveroo.modal.enitity.Restaurant;
import com.ecorp.deliveroo.repository.FeaturedRepository;
import com.ecorp.deliveroo.repository.RestaurantRepository;

@Service
public class FeaturedService {

	@Autowired
	private FeaturedRepository featuredRepository;

	@Autowired
	private RestaurantRepository restaurantRepository;

	public List<Featured> getAllFeatured() {
		return featuredRepository.findAll();
	}

	public Featured getFeaturedById(UUID id) {
		return featuredRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Featured not found with id: " + id));
	}

	public Featured getFeaturedByName(String name) {
		return featuredRepository.findByName(name)
				.orElseThrow(() -> new ResourceNotFoundException("Featured not found with name: " + name));
	}

	public Featured addFeatured(String name, String shortDescription, List<UUID> restaurantIds) {
		Featured featured = new Featured();
		featured.setName(name);
		featured.setShortDescription(shortDescription);

		List<Restaurant> restaurants = restaurantRepository.findAllById(restaurantIds);
		featured.setRestaurants(restaurants);

		return featuredRepository.save(featured);
	}

	public Featured updateFeatured(UUID id, String name, String shortDescription, List<UUID> restaurantIds) {
		Featured featured = getFeaturedById(id);
		featured.setName(name);
		featured.setShortDescription(shortDescription);

		List<Restaurant> restaurants = restaurantRepository.findAllById(restaurantIds);
		featured.setRestaurants(restaurants);

		return featuredRepository.save(featured);
	}

	public void deleteFeatured(UUID id) {
		featuredRepository.deleteById(id);
	}

}
