package com.lvtrai.dacn.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.lvtrai.dacn.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
	
	@EntityGraph(attributePaths = { "productImages" })
	List<Product> findAllEagerBy();	
		
	@EntityGraph(attributePaths = { "productImages"})
	Optional<Product> findById(Long id);
	
	@Query("SELECT DISTINCT pi.url FROM ProductImage pi")
	List<String> findAllProductImages();
	
}
