package com.model2.mvc.web.product;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.CookieGenerator;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;


//==> 상품관리 RestController
@RestController
@RequestMapping("/product/*")
public class ProductRestController {
	
	///Field
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	//setter Method 구현 않음
		
	public ProductRestController(){
		System.out.println("\n==> " + this.getClass() + " 의 default 생성자 실행");
	}
	
	
	//==> classpath:config/common.properties  ,  classpath:config/commonservice.xml 참조 할것
	//==> 아래의 두개를 주석을 풀어 의미를 확인 할것
	@Value("#{commonProperties['pageUnit']}")
	//@Value("#{commonProperties['pageUnit'] ? : 3}")
	int pageUnit;

	@Value("#{commonProperties['pageSize']}")
	// @Value("#{commonProperties['pageSize'] ? : 2}")
	int pageSize;
	
	
	@RequestMapping( value="json/getProduct/{prodNo}", method=RequestMethod.GET )
	public Product getProduct( @PathVariable int prodNo,
							   @CookieValue(value="history", required=false) String history,
							   HttpServletResponse response) throws Exception{
		
		System.out.println("/product/json/getProduct : GET");
		
		//Business Logic
//		CookieGenerator cookie = new CookieGenerator();
//		history = "history" + prodNo;
//		cookie.setCookieName("history");
//		cookie.addCookie(null, history);
		
		return productService.getProduct(prodNo);
	}
	
	
	@RequestMapping( value="json/addProduct", method=RequestMethod.POST )
	public Product addProduct( @RequestBody Product product) throws Exception{
		
		System.out.println("/product/json/addProduct : POST");
		
		//Business Logic
		System.out.println("product :: " + product);
		productService.addProduct(product);
		
		return product;
	}
	
	
	@RequestMapping( value="json/updateProductView/{prodNo}", method=RequestMethod.GET)
	public Product updateProductView( @PathVariable int prodNo ) throws Exception{
		
		System.out.println("/product/json/updateProductView : GET");
		
		//Business Logic
		return productService.getProduct(prodNo);
	}
	
	
	@RequestMapping( value="json/updateProduct", method=RequestMethod.POST)
	public Product updateProduct( @RequestBody Product product ) throws Exception{
		
		System.out.println("/product/json/updateProduct : GET");
		
		//Business Logic
		System.out.println("product :: " + product);
		productService.updateProduct(product);
		
		return product;
	}
	
	
	@RequestMapping( value="json/listProduct", method=RequestMethod.POST)
	public Map<String,Object> listProduct( @RequestBody Search search ) throws Exception{
		
		System.out.println("/product/json/listProduct : POST");
		
		System.out.println("search :: " + search);
		
		if(search.getCurrentPage() == 0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		// Business logic 수행
		Map<String , Object> map = productService.getProductList(search);
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		
		map.put("list", map.get("list"));
		map.put("resultPage", resultPage);
		
		return map;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}