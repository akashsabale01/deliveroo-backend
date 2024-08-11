package com.ecorp.deliveroo.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ecorp.deliveroo.modal.enitity.Category;
import com.ecorp.deliveroo.modal.enitity.Dish;
import com.ecorp.deliveroo.repository.CategoryRepository;
import com.ecorp.deliveroo.repository.DishRepository;

@Service
public class DishService {

	@Autowired
	private DishRepository dishRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	public Dish addDish(String name, String shortDescription, Integer price, MultipartFile imageFile,
			String categoryName) throws IOException {
		Dish dish = new Dish();
		dish.setName(name);
		dish.setShortDescription(shortDescription);
		dish.setPrice(price);

		if (imageFile != null && !imageFile.isEmpty()) {
			dish.setImageName(imageFile.getOriginalFilename());
			dish.setImageType(imageFile.getContentType());
			dish.setImage(imageFile.getBytes());
		}

		// Find category by name
		Category category = categoryRepository.findByName(categoryName)
				.orElseThrow(() -> new RuntimeException("Category not found"));
		dish.setCategory(category);

		return dishRepository.save(dish);
	}
	
	 public List<Dish> getAllDishes() {
	        return dishRepository.findAll();
	    }

}
