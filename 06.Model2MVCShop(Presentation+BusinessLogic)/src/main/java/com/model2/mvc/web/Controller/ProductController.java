package com.model2.mvc.web.Controller;

import java.io.File;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.multipart.MultipartFile;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.comment.CommentService;
import com.model2.mvc.service.domain.Comment;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.ReComment;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.recomment.ReCommentService;

@Controller
@RequestMapping("/product/*")
public class ProductController {
	
	//Filed
	
	@Autowired
	@Qualifier( value = "productServiceImpl")
	private ProductService productService;
	
	@Autowired
	@Qualifier(value = "purchaseServiceImpl")
	private PurchaseService PurchaseService;
	
	@Autowired
	@Qualifier(value = "commentServiceImpl")
	private CommentService commentService;
	
	@Autowired
	@Qualifier(value = "reCommentServiceImpl")
	private ReCommentService recommentService;
	
	public ProductController() {
		System.out.println(this.getClass()+"\n");
	}
	
	@Value("#{commonProperties['pageUnit']}")
	//@Value("#{commonProperties['pageUnit'] ?: 3}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	//@Value("#{commonProperties['pageSize'] ?: 2}")
	int pageSize;
	
	@Value("#{commonProperties['file.path']}")
	String filePath;
	
	//@RequestMapping(value = "/addProduct.do")
	@RequestMapping(value = "addProduct",  method=RequestMethod.POST)
	public String addProduct(@ModelAttribute("product") Product product, @RequestParam("uploadFile")MultipartFile file, 
															HttpSession session ,HttpServletRequest request) 
															throws Exception {
		System.out.println("addProduct : " + product);
		
		String date = product.getManuDate();
		
		  String fileName =  file.getOriginalFilename();
		  
		  product.setFileName(fileName);
		  
		  System.out.println("file의 정보는 : " + fileName);
		  
		  date = date.replaceAll("-", "");
		  
		  product.setManuDate(date);
		  file.transferTo(new File(filePath +"/"+ fileName));
//		  FileOutputStream fos = new FileOutputStream(file.transferTo((filePath +"/"+ fileName) ));
//		  fos.write(product.getFileName().getBytes()); fos.close();
		  
		  productService.insertProduct(product);
		 
		
		System.out.println("product: " + product + "\n");
		
		return "redirect:/index.jsp";		
	}
	//@RequestMapping(value = "/getProduct.do")
	@RequestMapping(value = "getProduct" , method = RequestMethod.GET)
	public String GetProduct(@RequestParam("prodNo")int prodNo,
							Model model,HttpSession session,
							HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		//브라우저에 쿠키 심기
		System.out.println("Cookie start" +  " \n");
		
		String prodNoo = Integer.toString(prodNo); 
		String val = prodNoo;
		
		Cookie[] cookies = request.getCookies();
		
		for (int i = 0; i < cookies.length; i++) {				
			if(cookies[i].getName().equals("history")) {
				val =  URLDecoder.decode(cookies[i].getValue()) + ","+ prodNoo;			
			}						
		}
		
		Cookie cookie = new Cookie("history",  URLEncoder.encode(val));	
		response.addCookie(cookie);
		 
		//GetProduct start
		System.out.println("getProduct start" + "\n");
		
		User user = (User)session.getAttribute("user");
		
		Product productVO = productService.getProduct(prodNo);
		
		
		Map<String , Object> map = commentService.listComment(prodNo);
		
		int[] commentNo = new int[map.size()];
		
		commentNo = (int[]) map.get("commentNo");
		List<Map<String, Object>> list = new ArrayList<>();; 
		
		for (int i = 0; i < commentNo.length; i++) {
			Map<String,Object> addMap = new HashMap<String, Object>();
			
			Map<String, Object>map2 = recommentService.listReComment(commentNo[i]);
			System.out.println("ReComment의 정보 입니다 : " + map2.get("list"));
			
			addMap.put("comment", commentService.getComment(commentNo[i]));
			addMap.put("recomment", recommentService.listReComment(commentNo[i]));
		
			list.add(addMap);
			System.out.println("commentNo : " +  list);
	
		}
		
		System.out.println("list의 정보 입니다 : " + list);
		model.addAttribute("list", list);
		
		model.addAttribute("productVO" , productVO);
		
		return "forward:/product/getProduct.jsp";
		
	}
	
	//@RequestMapping(value = "/updateProductView.do")
	@RequestMapping(value = "updateProductView", method = RequestMethod.GET)
	public String UpdateProductView(@RequestParam("prodNo")int prodNo, 
									Model model) throws Exception {
		System.out.println("updateProductView Start");
		
		Product product = productService.getProduct(prodNo);
		
		
		
		System.out.println("updateProductView.do \n");
		
		model.addAttribute("productVO" , product);			
		
		return "forward:/product/updateProductView.jsp";
	}
	
	//@RequestMapping(value = "/updateProduct.do")
	@RequestMapping(value = "updateProduct", method = RequestMethod.POST)
	public String UpdateProduct(@ModelAttribute("product") Product product, @RequestParam("uploadFile")MultipartFile file,
									Model model) throws Exception {
		
		System.out.println("updateProduct ");		
		String date = product.getManuDate();
		
		System.out.println("date" + date);
		
		date = date.replaceAll("-", "");
		
		String fileName =  file.getOriginalFilename();
		  
		product.setFileName(fileName);		  
		System.out.println("file의 정보는 : " + fileName);
		
		file.transferTo(new File(filePath +"/"+ fileName));
		  
		product.setManuDate(date);		
		
		productService.updateProduct(product);
		
		System.out.println("prodNo: " + product);
		
		
				
		return "redirect:/product/getProduct?prodNo="+product.getProdNo();
	}
	
	//@RequestMapping(value = "/listProduct.do")
	@RequestMapping(value = "listProduct")
	public String listProduct(@ModelAttribute("search")Search search,								
								Model model,
								HttpServletRequest request) throws Exception{
		
		System.out.println("listProduct");
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		Map<String , Object> map = productService.getProductList(search);
		
		Page resultPage = new Page( search.getCurrentPage(), 
						((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		
		System.out.println("resultPage의 정보" +resultPage + "\n");
		
		System.out.println("map :" + map);
		//Product product = product.setProTranCode();
		
		model.addAttribute("list", map.get("list"));
		model.addAttribute("searchVO", search);
		model.addAttribute("resultPage", resultPage);
		
		return "forward:/product/listProduct.jsp";
	}
}
