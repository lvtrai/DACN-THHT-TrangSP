package com.lvtrai.dacn.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lvtrai.dacn.model.Product;
import com.lvtrai.dacn.repository.ProductRepository;
import com.lvtrai.dacn.repository.ProductSpecification;
import com.lvtrai.dacn.service.ProductService;


@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	
	@Override
	public Page<Product> findProductsByCriteria(Pageable pageable, Integer priceLow, Integer priceHigh, List<String> productImages, String search) {		
		Page<Product> page = productRepository.findAll(ProductSpecification.filterBy(priceLow, priceHigh, productImages, search), pageable);
        return page;		
	}	
	
	
	@Override
	public Product findProductById(Long id) {
		Optional<Product> opt = productRepository.findById(id);
		return opt.get();
	}


	@Override
	@Cacheable("productImages")
	public List<String> getAllProductImages() {
		return productRepository.findAllProductImages();
	}
}
