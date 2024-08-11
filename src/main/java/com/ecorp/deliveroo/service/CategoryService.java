package com.ecorp.deliveroo.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ecorp.deliveroo.modal.dto.CategoryDTO;
import com.ecorp.deliveroo.modal.enitity.Category;
import com.ecorp.deliveroo.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public Category addCategory(String name, MultipartFile imageFile) throws IOException {
		Category category = new Category();
		category.setName(name);

		if (imageFile != null && !imageFile.isEmpty()) {
			category.setImageName(imageFile.getOriginalFilename());
			category.setImageType(imageFile.getContentType());
			category.setImage(imageFile.getBytes());
		}

		return categoryRepository.save(category);
	}

	public List<Category> getAllCategories() {
		return categoryRepository.findAll();
	}

	public Optional<Category> getCategoryByName(String name) {
		return categoryRepository.findByName(name);
	}

	public Optional<Category> getCategoryById(UUID id) {
		return categoryRepository.findById(id);
	}
}
