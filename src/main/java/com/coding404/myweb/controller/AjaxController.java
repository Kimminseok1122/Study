package com.coding404.myweb.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coding404.myweb.command.CategoryVO;
import com.coding404.myweb.command.TestCategoryVO;
import com.coding404.myweb.product.ProductService;
import com.coding404.myweb.test.TestService;

@RestController
public class AjaxController {
	
	@Value("${project.upload.path}")
	private String uploadpath;
	
	@Autowired
	//ProductService productService;
	private TestService testService;
	
	
	@GetMapping("/getCategory")
	public ArrayList<TestCategoryVO> getCategory(){
		
		ArrayList<TestCategoryVO> list = testService.getCategory();
		//화면에 필요한 형식으로 변경해서 List<map>로 반환해도 됩니다.
		
		
		return list;
	}
	
	//두,세번째 카테고리
//	@GetMapping("/getCategoryChild/{group_id}/{category_lv}/{category_detail_lv}")
//	public ArrayList<CategoryVO> geteCategoryChild(@PathVariable("group_id") String group_id,
//												   @PathVariable("category_lv") int category_lv,
//												   @PathVariable("category_detail_lv") int category_detail_lv){
//		
//		//마이바티스 전달을 위해 vo에 저장
//		CategoryVO vo = CategoryVO.builder()
//								  .group_id(group_id)
//								  .category_lv(category_lv)
//								  .category_detail_lv(category_detail_lv)
//								  .build();
//		//서비스 영역 호출(조회)
//		
//		ArrayList<CategoryVO> list = productService.getCategoryChild(vo);
//		
//		return list;
//	}
//	
	
	@GetMapping("/getCategoryChild/{group_id}/{category_lv}/{category_detail_lv}")
	public ArrayList<TestCategoryVO> getCategoryChild(@PathVariable("group_id") String group_id,
								   @PathVariable("category_lv") int category_lv,
								   @PathVariable("category_detail_lv") int category_detail_lv) {
		
	TestCategoryVO vo	=	TestCategoryVO.builder()
										  .group_id(group_id)
										  .category_lv(category_lv)
										  .category_detail_lv(category_detail_lv)
										  .build();
		
		ArrayList<TestCategoryVO> list = testService.getCategoryChild(vo);
		
		
		return list;
	}
	
	
	@GetMapping("/display")
	public byte[] display(@RequestParam("filename") String filename,
						  @RequestParam("uuid") String uuid,
						  @RequestParam("filepath") String filepath) {
		
		//System.out.println(filepath + "\\" + uuid + "_" + filename);
		
		File file = new File(uploadpath + "\\" + filepath + "\\" + uuid + "_" + filename);
		
		byte[] result = null;
		try {
			result = FileCopyUtils.copyToByteArray(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
}
