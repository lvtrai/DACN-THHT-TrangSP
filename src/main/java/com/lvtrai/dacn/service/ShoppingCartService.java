package com.lvtrai.dacn.service;

import com.lvtrai.dacn.model.CartItem;
import com.lvtrai.dacn.model.Product;
import com.lvtrai.dacn.model.ShoppingCart;
import com.lvtrai.dacn.model.User;

public interface ShoppingCartService {

	ShoppingCart getShoppingCart(User user);
	
	int getItemsNumber(User user);
	
	CartItem findCartItemById(Long cartItemId);
	
	CartItem addProductToShoppingCart(Product product, User user, int qty);
		
	void clearShoppingCart(User user);
	
	void updateCartItem(CartItem cartItem, Integer qty);

	void removeCartItem(CartItem cartItem);
		
}
