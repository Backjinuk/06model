package com.model2.mvc.web.Controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.cart.CartService;
import com.model2.mvc.service.domain.Cart;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.user.UserService;

@Controller
@RequestMapping("/cart/*")
public class CartController {
	
	@Autowired
	@Qualifier("cartServiceImpl")
	private CartService cartService;
	
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;
	
	@Value("#{commonProperties['pageUnit']}")
	//@Value("#{commonProperties['pageUnit'] ?: 3}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	//@Value("#{commonProperties['pageSize'] ?: 2}")
	int pageSize;
	
	
	//@RequestMapping("/addCartView.do")
	@RequestMapping(value = "addCartView" , method = RequestMethod.GET)
	public String addCartView(@RequestParam("prodNo")int prodNo,
							@ModelAttribute("product")Product product,
							HttpSession session,HttpServletRequest request,
							Model model) throws Exception {
		
		System.out.println("cartStart");
		
		System.out.println("prodNo의 정보 :" + prodNo);
		
		product = productService.getProduct(prodNo);
		
		System.out.println("product : " + product);
		
		model.addAttribute("product", product);
		model.addAttribute("userId", session.getAttribute("user")); 
		
		return "forward:/cart/addCartView.jsp";	
		
	}
	
	//@RequestMapping("/addCart.do")
	@RequestMapping(value = "addCart" , method = RequestMethod.POST)
	public String addCart(@ModelAttribute("product")Product product,
							HttpSession session,
							Model model) throws Exception {

		Cart cart = new Cart();
		
		System.out.println("cart add Start");
		
		User user = (User)session.getAttribute("user");
		
		cart.setProdNo(product);
		cart.setBuyerId(userService.getUser(user.getUserId()));
		cart.setCartCode("y");
		cart.setCartValue(product.getProductValNum()); 
		cartService.addCart(cart);
		
		System.out.println("product.getProductValNum()의 정보: " + product.getProductValNum());
		
		System.out.println("cartadd의 정보" + cart);
		
		return "redirect:/cart/getCartList";
	}
	//@RequestMapping("/deleteCart.do")
	@RequestMapping(value = "deleteCart" , method = RequestMethod.GET)
	public String deleteCart(@RequestParam("cartNo") int cartNo) throws Exception {
		
		System.out.println("deleteCart start");
		
		cartService.deleteCart(cartNo);
		
		return "redirect:/cart/getCartList";
	}
	//@RequestMapping("/getCartList.do")
	@RequestMapping(value = "getCartList")
	public String addCart(@ModelAttribute("search")Search search,		
							HttpSession session,HttpServletRequest request,							
							Model model) throws Exception {
	
		System.out.println("cartList start");
		
		if(search.getCurrentPage() ==0 ){ 
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		User userId = (User)session.getAttribute("user");  
		
		Map<String,Object> map = 
				cartService.getCartList(search, userId.getUserId()); 
		
		Page resultPage = new Page(search.getCurrentPage(),
				((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		
		System.out.println("resultpage:" + resultPage);
		System.out.println("search:" + search);
		
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);		
		
		return "forward:/cart/cartList.jsp";
	}
}
