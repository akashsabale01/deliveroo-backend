package com.ecorp.deliveroo.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ecorp.deliveroo.exceptions.ResourceNotFoundException;
import com.ecorp.deliveroo.modal.dto.RestaurantDTO;
import com.ecorp.deliveroo.modal.enitity.Category;
import com.ecorp.deliveroo.modal.enitity.Dish;
import com.ecorp.deliveroo.modal.enitity.Restaurant;
import com.ecorp.deliveroo.repository.CategoryRepository;
import com.ecorp.deliveroo.repository.DishRepository;
import com.ecorp.deliveroo.repository.RestaurantRepository;

@Service
public class RestaurantService {

	@Autowired
	private RestaurantRepository restaurantRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private DishRepository dishRepository;

	public List<Restaurant> getAllRestaurants() {
		return restaurantRepository.findAll();
	}

	public Restaurant getRestaurantById(UUID id) {
		return restaurantRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Restaurant not found with id: " + id));
	}

	public Restaurant getRestaurantByName(String name) {
		return restaurantRepository.findByName(name)
				.orElseThrow(() -> new ResourceNotFoundException("Restaurant not found with name: " + name));
	}

	public Restaurant createRestaurant(String name, String shortDescription, MultipartFile imageFile, BigDecimal lat,
			BigDecimal lon, String area, String city, String state, String country, String address, Integer rating,
			List<UUID> categoryIds, List<UUID> dishIds) throws IOException {
		Restaurant restaurant = new Restaurant();
		restaurant.setName(name);
		restaurant.setShortDescription(shortDescription);

		if (imageFile != null && !imageFile.isEmpty()) {
			restaurant.setImageName(imageFile.getOriginalFilename());
			restaurant.setImageType(imageFile.getContentType());
			restaurant.setImage(imageFile.getBytes());
		}

		restaurant.setLat(lat);
		restaurant.setLon(lon);
		restaurant.setArea(area);
		restaurant.setCity(city);
		restaurant.setState(state);
		restaurant.setCountry(country);
		restaurant.setAddress(address);
		restaurant.setRating(rating);

		List<Category> categories = categoryRepository.findAllById(categoryIds);
		restaurant.setCategories(categories);

		List<Dish> dishes = dishRepository.findAllById(dishIds);
		restaurant.setDishes(dishes);

		return restaurantRepository.save(restaurant);
	}

	public Restaurant updateRestaurant(UUID id, String name, String shortDescription, MultipartFile imageFile,
			BigDecimal lat, BigDecimal lon, String area, String city, String state, String country, String address,
			Integer rating, List<UUID> categoryIds, List<UUID> dishIds) throws IOException {
		Restaurant restaurant = getRestaurantById(id);
		restaurant.setName(name);
		restaurant.setShortDescription(shortDescription);

		if (imageFile != null && !imageFile.isEmpty()) {
			restaurant.setImageName(imageFile.getOriginalFilename());
			restaurant.setImageType(imageFile.getContentType());
			restaurant.setImage(imageFile.getBytes());
		}

		restaurant.setLat(lat);
		restaurant.setLon(lon);
		restaurant.setArea(area);
		restaurant.setCity(city);
		restaurant.setState(state);
		restaurant.setCountry(country);
		restaurant.setAddress(address);
		restaurant.setRating(rating);

		List<Category> categories = categoryRepository.findAllById(categoryIds);
		restaurant.setCategories(categories);

		List<Dish> dishes = dishRepository.findAllById(dishIds);
		restaurant.setDishes(dishes);

		return restaurantRepository.save(restaurant);
	}

	public void deleteRestaurant(UUID id) {
		restaurantRepository.deleteById(id);
	}

}