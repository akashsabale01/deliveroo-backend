package com.ecorp.deliveroo.modal.enitity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Dish {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(unique = true, nullable = false)
	private String name;

	private String shortDescription;

	private Integer price;

	private String imageName;

	private String imageType;

	@Lob
	@Column(columnDefinition = "LONGBLOB")
	private byte[] image;

	// Adding category field with ManyToOne relationship - many dishes can have same category
	@ManyToOne
	private Category category; 

}
