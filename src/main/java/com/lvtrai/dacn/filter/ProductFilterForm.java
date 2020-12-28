package com.lvtrai.dacn.filter;

import java.util.List;

public class ProductFilterForm {
	
	private List<String> productImage;
	private Integer pricelow;
	private Integer pricehigh;
	private String sort;
	private Integer page;
	private String search;
	
	public ProductFilterForm() {
	}

	
	public List<String> getProductImage() {
		return productImage;
	}


	public void setProductImage(List<String> productImage) {
		this.productImage = productImage;
	}


	public Integer getPricelow() {
		return pricelow;
	}

	public void setPricelow(Integer pricelow) {
		this.pricelow = pricelow;
	}

	public Integer getPricehigh() {
		return pricehigh;
	}

	public void setPricehigh(Integer pricehigh) {
		this.pricehigh = pricehigh;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}
	
	
	
}
