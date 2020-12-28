package com.lvtrai.dacn.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lvtrai.dacn.filter.ProductFilterForm;
import com.lvtrai.dacn.filter.SortFilter;
import com.lvtrai.dacn.model.Product;
import com.lvtrai.dacn.model.ProductImage;
import com.lvtrai.dacn.service.ProductService;

@Controller
public class StoreController {
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping("/store")
	public String store(@ModelAttribute("filters") ProductFilterForm filters, Model model) {
		Integer page = filters.getPage();			
		int pagenumber = (page == null ||  page <= 0) ? 0 : page-1;
		SortFilter sortFilter = new SortFilter(filters.getSort());	
		Page<Product> pageresult = productService.findProductsByCriteria(PageRequest.of(pagenumber,9, sortFilter.getSortType()), 
			filters.getPricelow(), filters.getPricehigh(), filters.getProductImage(), filters.getSearch());	
		model.addAttribute("allProductImages", productService.getAllProductImages());
		model.addAttribute("products", pageresult.getContent());
		model.addAttribute("totalitems", pageresult.getTotalElements());
		model.addAttribute("itemsperpage", 9);
		return "store";
	}
	
	
	@RequestMapping("/product-detail")
	public String articleDetail(@PathParam("id") Long id, Model model) {
		Product product = productService.findProductById(id);		
		String preselectedProductImages = "";
		for (ProductImage productImage : product.getProductImages()) {
			preselectedProductImages += (productImage.getUrl() + ",");
		}	
		model.addAttribute("preselectedProductImages", preselectedProductImages);
		model.addAttribute("product", product);
		model.addAttribute("notEnoughStock", model.asMap().get("notEnoughStock"));
		model.addAttribute("addProductSuccess", model.asMap().get("addProductSuccess"));
		return "productdetail";
	}
	

}
