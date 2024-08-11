package com.ecorp.deliveroo.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Dish> addDish(@RequestParam("name") String name,
			@RequestParam("shortDescription") String shortDescription, @RequestParam("price") Integer price,
			@RequestParam("image") MultipartFile imageFile, @RequestParam("categoryName") String categoryName) {
		try {
			Dish dish = dishService.addDish(name, shortDescription, price, imageFile, categoryName);
			return new ResponseEntity<>(dish, HttpStatus.CREATED);
		} catch (IOException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}catch(Exception e) {
			System.out.println(e.toString());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping
	public ResponseEntity<List<Dish>> getAllDishes() {
		List<Dish> dishes = dishService.getAllDishes();
		return new ResponseEntity<>(dishes, HttpStatus.OK);
	}
}
