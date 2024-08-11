package com.ecorp.deliveroo.controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ecorp.deliveroo.modal.dto.RestaurantDTO;
import com.ecorp.deliveroo.modal.enitity.Restaurant;
import com.ecorp.deliveroo.service.RestaurantService;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

	@Autowired
	private RestaurantService restaurantService;

	@GetMapping
	public List<Restaurant> getRestaurants() {
		return restaurantService.getAllRestaurants();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Restaurant> getRestaurantById(@PathVariable UUID id) {
		Restaurant restaurant = restaurantService.getRestaurantById(id);
		return ResponseEntity.ok(restaurant);
	}

	@GetMapping("/byName/{name}")
	public ResponseEntity<Restaurant> getRestaurantByName(@PathVariable String name) {
		Restaurant restaurant = restaurantService.getRestaurantByName(name);
		return ResponseEntity.ok(restaurant);
	}

	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Restaurant> createRestaurant(@RequestParam("name") String name,
			@RequestParam("shortDescription") String shortDescription, @RequestParam("image") MultipartFile imageFile,
			@RequestParam("lat") BigDecimal lat, @RequestParam("lon") BigDecimal lon, @RequestParam("area") String area,
			@RequestParam("city") String city, @RequestParam("state") String state,
			@RequestParam("country") String country, @RequestParam("address") String address,
			@RequestParam("rating") Integer rating, @RequestParam("categoryIds") List<UUID> categoryIds,
			@RequestParam("dishIds") List<UUID> dishIds) throws IOException {
		Restaurant newRestaurant = restaurantService.createRestaurant(name, shortDescription, imageFile, lat, lon, area,
				city, state, country, address, rating, categoryIds, dishIds);
		return ResponseEntity.status(HttpStatus.CREATED).body(newRestaurant);
	}

	@PutMapping(path = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Restaurant> updateRestaurant(@PathVariable UUID id, @RequestParam("name") String name,
			@RequestParam("shortDescription") String shortDescription, @RequestParam("image") MultipartFile imageFile,
			@RequestParam("lat") BigDecimal lat, @RequestParam("lon") BigDecimal lon, @RequestParam("area") String area,
			@RequestParam("city") String city, @RequestParam("state") String state,
			@RequestParam("country") String country, @RequestParam("address") String address,
			@RequestParam("rating") Integer rating, @RequestParam("categoryIds") List<UUID> categoryIds,
			@RequestParam("dishIds") List<UUID> dishIds) throws IOException {
		Restaurant newRestaurant = restaurantService.updateRestaurant(id, name, shortDescription, imageFile, lat, lon,
				area, city, state, country, address, rating, categoryIds, dishIds);
		return ResponseEntity.status(HttpStatus.CREATED).body(newRestaurant);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteRestaurant(@PathVariable UUID id) {
		restaurantService.deleteRestaurant(id);
		return ResponseEntity.noContent().build();
	}
}
