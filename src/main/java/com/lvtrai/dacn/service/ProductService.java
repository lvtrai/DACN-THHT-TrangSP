package com.lvtrai.dacn.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.lvtrai.dacn.model.Product;

public interface ProductService {
	
	Page<Product> findProductsByCriteria(Pageable pageable, Integer priceLow, Integer priceHigh, List<String> productImages, String search);
		
//	List<Product> findFirstProducts();

	Product findProductById(Long id);
		
	List<String> getAllProductImages();

}
