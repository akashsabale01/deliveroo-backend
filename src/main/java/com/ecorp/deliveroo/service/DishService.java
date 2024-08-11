package com.ecorp.deliveroo.service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ecorp.deliveroo.exceptions.ResourceNotFoundException;
import com.ecorp.deliveroo.modal.enitity.Dish;
import com.ecorp.deliveroo.repository.DishRepository;

@Service
public class DishService {

	@Autowired
	private DishRepository dishRepository;

	public List<Dish> getAllDishes() {
		return dishRepository.findAll();
	}

	public Dish getDishById(UUID id) {
		return dishRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Dish not found with id: " + id));
	}

	public Dish getDishByName(String name) {
		return dishRepository.findByName(name)
				.orElseThrow(() -> new ResourceNotFoundException("Dish not found with name: " + name));
	}

	public Dish addDish(String name, String shortDescription, Integer price, MultipartFile imageFile)
			throws IOException {
		Dish dish = new Dish();
		dish.setName(name);
		dish.setShortDescription(shortDescription);
		dish.setPrice(price);

		if (imageFile != null && !imageFile.isEmpty()) {
			dish.setImageName(imageFile.getOriginalFilename());
			dish.setImageType(imageFile.getContentType());
			dish.setImage(imageFile.getBytes());
		}

		return dishRepository.save(dish);
	}

	public Dish updateDish(UUID id, String name, String shortDescription, Integer price, MultipartFile imageFile)
			throws IOException {
		Dish dish = getDishById(id);
		dish.setName(name);
		dish.setShortDescription(shortDescription);
		dish.setPrice(price);

		if (imageFile != null && !imageFile.isEmpty()) {
			dish.setImageName(imageFile.getOriginalFilename());
			dish.setImageType(imageFile.getContentType());
			dish.setImage(imageFile.getBytes());
		}

		return dishRepository.save(dish);
	}

	public void deleteDish(UUID id) {
		dishRepository.deleteById(id);
	}

}
