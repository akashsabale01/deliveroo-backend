package com.ecorp.deliveroo.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
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

import com.ecorp.deliveroo.modal.enitity.Category;
import com.ecorp.deliveroo.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping
	public ResponseEntity<List<Category>> getAllCategories() {
		List<Category> categories = categoryService.getAllCategories();
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}

	@GetMapping("name/{name}")
	public ResponseEntity<Category> getCategoryByName(@PathVariable String name) {
		Category category = categoryService.getCategoryByName(name);
		return ResponseEntity.ok(category);
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<Category> getCategoryById(@PathVariable UUID id) {
		Category category = categoryService.getCategoryById(id);
		return ResponseEntity.ok(category);
	}

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

	@PutMapping("/{id}")
	public ResponseEntity<Category> updateCategory(@PathVariable UUID id, @RequestParam("name") String name,
			@RequestParam(value = "image", required = false) MultipartFile imageFile) throws IOException {
		Category category = categoryService.updateCategory(id, name, imageFile);
		return ResponseEntity.ok(category);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCategory(@PathVariable UUID id) {
		categoryService.deleteCategory(id);
		return ResponseEntity.noContent().build();
	}

}
