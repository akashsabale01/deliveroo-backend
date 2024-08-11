package com.ecorp.deliveroo.modal.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RestaurantDTO {

	private String name;
	private String shortDescription;
	private BigDecimal lat;
	private BigDecimal lon;
	private String area;
	private String city;
	private String state;
	private String country;
	private String address;
	private Integer rating;
	private List<UUID> categoryIds;
	private List<UUID> dishIds;
}
