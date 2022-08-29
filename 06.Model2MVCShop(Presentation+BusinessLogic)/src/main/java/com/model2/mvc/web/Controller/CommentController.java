package com.model2.mvc.web.Controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.model2.mvc.service.comment.CommentService;
import com.model2.mvc.service.domain.Comment;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;

@Controller
@RequestMapping("/comment/*")
public class CommentController {
	
	@Autowired
	@Qualifier( value = "commentServiceImpl")
	private CommentService commentService;
	
	@Autowired
	@Qualifier(value = "productServiceImpl")
	private ProductService productService;
	
	
	public CommentController() {
		System.out.println(this.getClass()+"\n");
	}
	
	@RequestMapping(value ="addComment", method = RequestMethod.POST)
	public String addComment(@ModelAttribute("comment")Comment comment,
							@RequestParam("productNo")int prodNo,
								HttpSession session) throws Exception {
		
		System.out.println("addComentStart : " + prodNo);
		comment.setProdNo(productService.getProduct(prodNo));
		comment.setUserId((User)session.getAttribute("user"));
		
		System.out.println("comment의 정보 : " + comment.getProdNo().getProdNo() + " $$ "
						+comment.getUserId().getUserId()  );
		
		commentService.addComment(comment);
		
		return "redirect:/product/getProduct?prodNo="+comment.getProdNo().getProdNo();
	}
	
	@RequestMapping(value = "updateComment", method = RequestMethod.GET)
	public String updateComment(@RequestParam("commentNo")int commentNo,
								@RequestParam("commentTitle")String commentTitle,
								@RequestParam("prodNoo")int prodNo,
								@ModelAttribute("comment")Comment comment) throws Exception {
		
		System.out.println("updateComment 내용 : " + commentTitle +":" + commentNo );
		
		comment.setProdNo(productService.getProduct(prodNo));
		comment.setCommentNo(commentNo);
		comment.setCommentTitle(commentTitle);
		
		commentService.updateComment(comment);
		 
		return "redirect:/product/getProduct?prodNo="+comment.getProdNo().getProdNo();
	}
	
	@RequestMapping(value = "delectComment", method = RequestMethod.GET)
	public String deleteComent(@RequestParam("prodNoo")int prodNo,
								@RequestParam("commentNo")int comentNo,
								@ModelAttribute("comment")Comment comment) throws Exception {
	
	comment.setCommentNo(comentNo);
	comment.setProdNo(productService.getProduct(prodNo));	
	 
	commentService.deleteComment(comment);
	
	return "redirect:/product/getProduct?prodNo="+comment.getProdNo().getProdNo();
	}
	
}
