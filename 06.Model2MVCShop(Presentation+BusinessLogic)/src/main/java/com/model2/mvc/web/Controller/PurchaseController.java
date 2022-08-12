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
import org.springframework.web.context.annotation.RequestScope;
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
@RequestMapping("/purchase/*")
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
	
	//@RequestMapping("/addPurchaseView.do")
	@RequestMapping(value = "addPurchaseView" , method = RequestMethod.GET)
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
	//@RequestMapping("/addPurchase.do")
	@RequestMapping(value = "addpurchase" , method = RequestMethod.POST)
	public ModelAndView addPurchase(@ModelAttribute("productVO")Product productVO,
									HttpSession session, HttpServletRequest requset,
									@ModelAttribute("purchaseVO")Purchase purchaseVO,
									Model model) throws Exception {
		
		purchaseVO.setPurchaseProd(productVO);
		//user.getUserId()
		purchaseVO.setBuyer((User)session.getAttribute("user"));
		
		purchaseService.addPurchase(purchaseVO);
		
		//purchaseService.updatePurchase(purchaseVO);
		
		System.out.println("purchaseVO 의 정보 :" + purchaseVO);
		
		model.addAttribute("purchaseVO", purchaseVO);
		session.setAttribute("buyer", purchaseVO.getBuyer());
		
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("forward:/purchase/addpurchase.jsp");
		
		return mv;
	}
//	//@RequestMapping("/addCartPurchase.do")
//	@RequestMapping(value = "addcartPu")
//	public ModelAndView addCartPurchase(@RequestParam("prodNo") int prodNo,
//										HttpSession session, HttpServletRequest requset,
//										@ModelAttribute("purchaseVO")Purchase purchaseVO,
//										Model model) throws Exception {
//		
//		purchaseVO.setPurchaseProd(productService.getProduct(prodNo));
//		purchaseVO.setBuyer((User)session.getAttribute("user"));
//		purchaseVO.setDivyAddr(null);
//		purchaseVO.setDivyDate(null);
//		purchaseVO.setDivyRequest(null);
//		purchaseVO.setOrderDate(null);
//		purchaseVO.setReceiverName(null);
//		purchaseVO.setReceiverPhone(null);
//		
//		purchaseService.addPurchase(purchaseVO);
//		
//		//purchaseService.updatePurchase(purchaseVO);
//		
//		System.out.println("purchaseVO 의 정보 :" + purchaseVO);
//		
//		model.addAttribute("purchaseVO", purchaseVO);
//		session.setAttribute("buyer", purchaseVO.getBuyer());
//		
//		ModelAndView mv = new ModelAndView();
//		
//		mv.setViewName("redirect:/getCartList.do");
//		
//		return mv;
//	}
	
	//@RequestMapping("/getPurchase.do")
	@RequestMapping(value = "getPurchase" , method = RequestMethod.GET)
	public ModelAndView getPurchase(@ModelAttribute("purchase")Purchase purchase,
									Model model) throws Exception {
		System.out.println("getPurchase start");
		
		purchase = purchaseService.getPurchase(purchase.getTranNo());
		
		System.out.println("purchase의 정보:" + purchase);
		
		model.addAttribute("purchaseVO", purchase);
		
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("forward:/purchase/getPurchase.jsp");
		
		return mv;
		
	}
	//@RequestMapping("/updateTranCodeByProd.do")
	@RequestMapping(value = "updateTranCodeByProd", method = RequestMethod.POST)
	public ModelAndView updateTranCodeByProd(@RequestParam("prodNo")int prodNo,
											@RequestParam("tranCode")String tranCode,
											@ModelAttribute("purchase")Purchase purchase,
											HttpServletRequest request,	Model model) throws Exception {
		
		
		purchase.setTranCode("배송완료");
		purchase.setPurchaseProd(productService.getProduct(
				Integer.parseInt(request.getParameter("prodNo"))));
		
		purchaseService.updateTranCode(purchase); 
		
		System.out.println("purchase 변경후" + purchase);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/listProduct.do?menu=mange");
		
		return mv;
	}
	 
	
	//@RequestMapping("/listPurchase.do")
	@RequestMapping(value = "listPurchase")
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
