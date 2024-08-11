package com.ecorp.deliveroo.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ecorp.deliveroo.modal.dto.CategoryDTO;
import com.ecorp.deliveroo.modal.enitity.Category;
import com.ecorp.deliveroo.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<Category> addCategory(@RequestParam("name") String name,
			@RequestParam("image") MultipartFile imageFile) {
		try {
			Category category = categoryService.addCategory(name, imageFile);
			return new ResponseEntity<>(category, HttpStatus.CREATED);
		} catch (IOException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping
	public ResponseEntity<List<Category>> getAllCategories() {
		List<Category> categories = categoryService.getAllCategories();
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}

	@GetMapping("name/{name}")
	public ResponseEntity<Category> getCategory(@PathVariable String name) {
		Optional<Category> category = categoryService.getCategoryByName(name);

		if (category.isPresent()) {
			return new ResponseEntity<>(category.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<Category> getCategoryById(@PathVariable UUID id) {
		Optional<Category> category = categoryService.getCategoryById(id);
		if (category.isPresent()) {
			return new ResponseEntity<>(category.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
