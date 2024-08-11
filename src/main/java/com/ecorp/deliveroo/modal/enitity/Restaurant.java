package com.ecorp.deliveroo.modal.enitity;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Restaurant {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(nullable = false)
	private String name;

	private String shortDescription;

	private String imageName;

	private String imageType;

	@Lob
	@Column(columnDefinition = "LONGBLOB")
	private byte[] image;

	@Column(precision = 20, scale = 6)
	private BigDecimal lat;

	@Column(precision = 20, scale = 6)
	private BigDecimal lon;

	private String area;

	private String city;

	private String state;

	private String country;

	private String address;

	private Integer rating;

	@ManyToMany
	@JoinTable(name = "restaurant_categories", joinColumns = @JoinColumn(name = "restaurant_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
	private List<Category> categories;

	@ManyToMany
	@JoinTable(name = "restaurant_dishes", joinColumns = @JoinColumn(name = "restaurant_id"), inverseJoinColumns = @JoinColumn(name = "dish_id"))
	private List<Dish> dishes;
}