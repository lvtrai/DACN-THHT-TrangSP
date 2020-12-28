package com.lvtrai.dacn.service;

import java.util.List;

import com.lvtrai.dacn.model.Order;
import com.lvtrai.dacn.model.Payment;
import com.lvtrai.dacn.model.Shipping;
import com.lvtrai.dacn.model.ShoppingCart;
import com.lvtrai.dacn.model.User;

public interface OrderService {

	Order createOrder(ShoppingCart shoppingCart, Shipping shippingAddress, Payment payment, User user);
	
	List<Order> findByUser(User user);
	
	Order findOrderWithDetails(Long id);
}
