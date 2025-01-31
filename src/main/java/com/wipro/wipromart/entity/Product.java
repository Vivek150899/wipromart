package com.wipro.wipromart.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter   //by the help of lombok we can generate
@Getter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="product_tbl")
public class Product {
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private long productId;
	
	@NotNull
	@Column(name="product_name", length=50)
	private String productName;
	
	@Positive
	@Column(name="product_price")
	private double productPrice;
	
	@PastOrPresent
	@Column(name="product_mfd")
	private LocalDate mfd;
	
	private String category;

}
