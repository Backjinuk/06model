package com.model2.mvc.web.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.model2.mvc.service.comment.CommentService;
import com.model2.mvc.service.domain.ReComment;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.recomment.ReCommentService;

@Controller
@RequestMapping("/recomment/*")
public class ReCommentController {
	
	@Autowired
	@Qualifier(value = "commentServiceImpl")
	private CommentService commentService;
	
	@Autowired
	@Qualifier(value = "reCommentServiceImpl")
	private ReCommentService recommentService;
	
	@Autowired
	@Qualifier(value = "productServiceImpl")
	private ProductService productService;
	
	public ReCommentController() {
		System.out.println("::" + this.getClass() + "\n");
	}
	
	@RequestMapping(value = "addReComment", method = RequestMethod.GET)
	public String addReComment(@RequestParam("recommentTitlee")String recommentTitle,
								@RequestParam("recommentNoo")int recommentNo,
								@RequestParam("prodNoo")int prodNo,
								@ModelAttribute("recomment")ReComment recomment,
								HttpSession session) throws Exception {
		System.out.println("addReComment : " + recommentTitle +"::" + recommentNo + " :: " + prodNo );
		 
		recomment.setCommentNo(commentService.getComment(recommentNo));
		recomment.setProdNo(productService.getProduct(prodNo));
		recomment.setRecommentTitle(recommentTitle);
		recomment.setUserId((User)session.getAttribute("user"));
		
		recommentService.addReComment(recomment);
		 
		return "redirect:/product/getProduct?prodNo="+recomment.getProdNo().getProdNo();
	}
	@RequestMapping(value = "updateReComment", method = RequestMethod.GET)
	public String updateReComment(@RequestParam("recommentTitlee") String recommentTitle,
									@RequestParam("prodNoo") int prodNo,
									@RequestParam("recommentNoo") int recommentNo,
									@ModelAttribute("recomment")ReComment recomment) throws Exception {
		
		System.out.println("updateReComment¿« ¡§∫∏: " + recommentTitle + " : " + recommentNo);
		
		recomment.setRecommentNo(recommentNo);
		recomment.setRecommentTitle(recommentTitle);
		
		recommentService.updateReComment(recomment);
	return "redirect:/product/getProduct?prodNo="+prodNo;
	}
	
	@RequestMapping(value = "deleteReComment" , method = RequestMethod.GET)
	public String deleteReComment(@RequestParam("recommentNoo")int recommentNo,
									@RequestParam("prodNoo")int prodNo,
									@ModelAttribute("recomment")ReComment recomment) throws Exception {
		System.out.println("deleteReComment : " + recommentNo + " : " + prodNo);
		
		recommentService.deleteReComment(recommentNo);
		
		return "redirect:/product/getProduct?prodNo="+prodNo;
	}
}
