package com.lvtrai.dacn.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lvtrai.dacn.model.CartItem;
import com.lvtrai.dacn.model.Product;
import com.lvtrai.dacn.model.ShoppingCart;
import com.lvtrai.dacn.model.User;
import com.lvtrai.dacn.service.ProductService;
import com.lvtrai.dacn.service.ShoppingCartService;

@Controller
@RequestMapping("/shopping-cart")
public class ShoppingCartController {
		
	@Autowired
	private ProductService productService;
	
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@RequestMapping("/cart")
	public String shoppingCart(Model model, Authentication authentication) {		
		User user = (User) authentication.getPrincipal();		
		ShoppingCart shoppingCart = shoppingCartService.getShoppingCart(user);		
		model.addAttribute("allProductImages", productService.getAllProductImages());
		model.addAttribute("cartItemList", shoppingCart.getCartItems());
		model.addAttribute("shoppingCart", shoppingCart);		
		return "shoppingcart";
	}

	@RequestMapping("/add-item")
	public String addItem(@ModelAttribute("product") Product product, @RequestParam("qty") String qty,
			 RedirectAttributes attributes, Model model, Authentication authentication, HttpServletRequest request) {
product = productService.findProductById(product.getId());				
		if (!product.hasAmount(Integer.parseInt(qty))) {
			attributes.addFlashAttribute("notEnoughStock", true);
			return "redirect:/product-detail?id="+product.getId();
		}	
		 HttpSession session = request.getSession();
         
		session.setAttribute("images", "/images/logo.png");
		System.out.println(qty +"hahahahahahahahahhahah");
		User user = (User) authentication.getPrincipal();		
		shoppingCartService.addProductToShoppingCart(product, user, Integer.parseInt(qty));
		attributes.addFlashAttribute("addProductSuccess", true);
		return "redirect:/product-detail?id="+product.getId();
	}
	
	@RequestMapping("/update-item")
	public String updateItemQuantity(@RequestParam("id") Long cartItemId,
									 @RequestParam("qty") Integer qty, Model model) {		
		CartItem cartItem = shoppingCartService.findCartItemById(cartItemId);
		if (cartItem.canUpdateQty(qty)) {
			shoppingCartService.updateCartItem(cartItem, qty);
		}
		return "redirect:/shopping-cart/cart";
	}
	
	@RequestMapping("/remove-item")
	public String removeItem(@RequestParam("id") Long id) {		
		shoppingCartService.removeCartItem(shoppingCartService.findCartItemById(id));		
		return "redirect:/shopping-cart/cart";
	} 
}
