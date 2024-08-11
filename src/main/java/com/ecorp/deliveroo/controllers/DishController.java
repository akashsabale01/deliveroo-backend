package com.ecorp.deliveroo.controllers;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ecorp.deliveroo.modal.enitity.Dish;
import com.ecorp.deliveroo.service.DishService;

@RestController
@RequestMapping("/api/dishes")
public class DishController {

	@Autowired
	private DishService dishService;

	@GetMapping
	public ResponseEntity<List<Dish>> getAllDishes() {
		List<Dish> dishes = dishService.getAllDishes();
		return new ResponseEntity<>(dishes, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Dish> getDishById(@PathVariable UUID id) {
		Dish dish = dishService.getDishById(id);
		return ResponseEntity.ok(dish);
	}

	@GetMapping("/byName/{name}")
	public ResponseEntity<Dish> getDishByName(@PathVariable String name) {
		Dish dish = dishService.getDishByName(name);
		return ResponseEntity.ok(dish);
	}

	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Dish> addDish(@RequestParam("name") String name,
			@RequestParam("shortDescription") String shortDescription, @RequestParam("price") Integer price,
			@RequestParam("image") MultipartFile imageFile) {
		try {
			Dish dish = dishService.addDish(name, shortDescription, price, imageFile);
			return new ResponseEntity<>(dish, HttpStatus.CREATED);
		} catch (IOException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Dish> updateDish(@PathVariable UUID id, @RequestParam("name") String name,
			@RequestParam("shortDescription") String shortDescription, @RequestParam("price") Integer price,
			@RequestParam(value = "image", required = false) MultipartFile imageFile) throws IOException {
		Dish dish = dishService.updateDish(id, name, shortDescription, price, imageFile);
		return ResponseEntity.ok(dish);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteDish(@PathVariable UUID id) {
		dishService.deleteDish(id);
		return ResponseEntity.noContent().build();
	}
}
