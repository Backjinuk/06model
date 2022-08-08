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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.user.UserService;

@Controller
public class PurchaseController {

	
	@Autowired
	@Qualifier(value = "purchaseServiceImpl")
	private PurchaseService purchaseService;
	
	@Autowired
	@Qualifier(value = "productServiceImpl")
	private ProductService productService;
	 
	@Autowired
	@Qualifier(value =  "userServiceImpl")
	private UserService userService;
	
	
	public PurchaseController() {
		System.out.println(this.getClass());
	}
	
	@Value("#{commonProperties['pageUnit']}")
	//@Value("#{commonProperties['pageUnit'] ?: 3}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	//@Value("#{commonProperties['pageSize'] ?: 2}")
	int pageSize;
	
	@RequestMapping("/addPurchaseView.do")
	public ModelAndView addPurchaseView(@RequestParam("prodNo") int prodNo,
										HttpSession session, HttpServletRequest requset,
										Model model) throws Exception {
		System.out.println("addPurchaseView start");
		
		Product product = productService.getProduct(prodNo);
		
		
		model.addAttribute("productVO", product);
		model.addAttribute("userVO", session.getAttribute("user"));
		
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("forward:/purchase/addPurchaseView.jsp");
		
		return mv;
	}
	@RequestMapping("/addPurchase.do")
	public ModelAndView addPurchase(@ModelAttribute("productVO")Product productVO,
									HttpSession session, HttpServletRequest requset,
									@ModelAttribute("purchaseVO")Purchase purchaseVO,
									Model model) throws Exception {
		
		purchaseVO.setPurchaseProd(productVO);
		//user.getUserId()
		purchaseVO.setBuyer((User)session.getAttribute("user"));
		
		purchaseService.addPurchase(purchaseVO);
		
		System.out.println("purchaseVO ÀÇ Á¤º¸ :" + purchaseVO);
		
		model.addAttribute("purchaseVO", purchaseVO);
		session.setAttribute("buyer", purchaseVO.getBuyer());
		
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("forward:/purchase/addpurchase.jsp");
		
		return mv;
	}
	@RequestMapping("/listPurchase.do")
	public ModelAndView listPurchase(@ModelAttribute("purchaseVO")Purchase purchase,
									HttpSession session, HttpServletRequest requset,
									@ModelAttribute("search") Search search, 
									Model model) throws Exception {
		System.out.println("listPurchase start");		
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);		
		
		
		User user =  (User)session.getAttribute("user");
		
		System.out.println(" user.getUserId:" +  user.getUserId());
		
		Map<String,Object> map = 
				purchaseService.getPurchaseList(search, user.getUserId());
		
		Page resultPage = new Page( search.getCurrentPage(), 
				((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		
		System.out.println("resultpage:" + resultPage);
		System.out.println("search:" + search);
		
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		
		ModelAndView mv = new ModelAndView();		
		mv.setViewName("forward:/purchase/listPurchase.jsp");
		
		return mv;
		
	}
}
