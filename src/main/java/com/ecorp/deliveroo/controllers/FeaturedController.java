package com.ecorp.deliveroo.controllers;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecorp.deliveroo.modal.enitity.Featured;
import com.ecorp.deliveroo.service.FeaturedService;

@RestController
@RequestMapping("/api/featured")
public class FeaturedController {

	@Autowired
	private FeaturedService featuredService;

	@GetMapping
	public List<Featured> getAllFeatured() {
		return featuredService.getAllFeatured();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Featured> getFeaturedById(@PathVariable UUID id) {
		Featured featured = featuredService.getFeaturedById(id);
		return ResponseEntity.ok(featured);
	}

	@GetMapping("/byName/{name}")
	public ResponseEntity<Featured> getFeaturedByName(@PathVariable String name) {
		Featured featured = featuredService.getFeaturedByName(name);
		return ResponseEntity.ok(featured);
	}

	@PostMapping
	public ResponseEntity<Featured> addFeatured(@RequestParam("name") String name,
			@RequestParam("shortDescription") String shortDescription,
			@RequestParam("restaurantIds") List<UUID> restaurantIds) {

		Featured newFeatured = featuredService.addFeatured(name, shortDescription, restaurantIds);

		return ResponseEntity.status(HttpStatus.CREATED).body(newFeatured);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Featured> updateFeatured(@PathVariable UUID id, @RequestParam("name") String name,
			@RequestParam("shortDescription") String shortDescription,
			@RequestParam("restaurantIds") List<UUID> restaurantIds) {
		Featured featured = featuredService.updateFeatured(id, name, shortDescription, restaurantIds);
		return ResponseEntity.ok(featured);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteFeatured(@PathVariable UUID id) {
		featuredService.deleteFeatured(id);
		return ResponseEntity.noContent().build();
	}
}
