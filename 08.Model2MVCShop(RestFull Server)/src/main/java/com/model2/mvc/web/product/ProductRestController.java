package com.model2.mvc.web.product;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.user.UserService;


//==> 惑前包府 RestController
@RestController
@RequestMapping("/product/*")
public class ProductRestController {
	
	///Field
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	//setter Method 备泅 臼澜
		
	public ProductRestController(){
		System.out.println("\n==> " + this.getClass() + " 狼 default 积己磊 角青");
	}
	
	
//	@RequestMapping( value="json/getUser/{userId}", method=RequestMethod.GET )
//	public User getUser( @PathVariable String userId ) throws Exception{
//		
//		System.out.println("/user/json/getUser : GET");
//		
//		//Business Logic
//		return userService.getUser(userId);
//	}
//	
//
//	@RequestMapping( value="json/login", method=RequestMethod.POST )
//	public User login(	@RequestBody User user,
//									HttpSession session ) throws Exception{
//	
//		System.out.println("/user/json/login : POST");
//		
//		//Business Logic
//		System.out.println("user :: " + user);
//		User dbUser = userService.getUser(user.getUserId());
//		
//		if( user.getPassword().equals(dbUser.getPassword())){
//			session.setAttribute("user", dbUser);
//		}
//		
//		return dbUser;
//	}
	
	
	@RequestMapping( value="json/addProduct", method=RequestMethod.POST )
	public Product addProduct( @RequestBody Product product) throws Exception{
		
		System.out.println("/product/json/addProduct : POST");
		
		//Business Logic
		System.out.println("product :: " + product);
		productService.addProduct(product);
		
		return product;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}