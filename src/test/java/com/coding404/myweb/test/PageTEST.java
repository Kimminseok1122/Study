package com.coding404.myweb.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.coding404.myweb.command.Practice01VO;
import com.coding404.myweb.command.ProductVO;
import com.coding404.myweb.command.TestVO;
import com.coding404.myweb.practice01.Practice01Mapper;
import com.coding404.myweb.product.ProductMapper;
import com.coding404.myweb.util.Criteria;
import com.coding404.myweb.util.PageVO;

@SpringBootTest
public class PageTEST {
	
	@Autowired
	ProductMapper productMapper;
	
	@Autowired
	Practice01Mapper practice01Mapper;
	
	@Autowired
	TestMapper testmapper;
	
	@Test
	public void test01() {
		
		for(int i = 1; i <= 223; i++) {
			
	TestVO  vo	= TestVO.builder()
				  .test_enddate("2022-04-09")
				  .test_writer("admin")
				  .test_price(10*i + 100*i + 100*i)
				  .test_count(30)
				  .test_discount(15)
				  .test_prod_purchase_yn("N")
				  .test_content("섹스가"+i)
				  .test_comment("하고싶다"+i)
				  .test_name("오나홀"+i)
				  .build();
				
			testmapper.testregist(vo);
		}
	}
	
//	@Test
//	public void test02() {
//		
//		Criteria cri = new Criteria(11,10);
//		PageVO pagevo = new PageVO(cri, 151);
//		
//		System.out.println(pagevo.toString());
//		
//		
//	}
	
	
}
