package com.coding404.myweb.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.coding404.myweb.TestUtil.Criteria;
import com.coding404.myweb.TestUtil.pageVO;
import com.coding404.myweb.command.TestUploadVO;
import com.coding404.myweb.command.TestVO;
import com.coding404.myweb.test.TestService;

@Controller
@RequestMapping("/test")
public class TestController {
	
	@Value("${project.upload.path}")
	private String uploadpath;

	@Autowired
	@Qualifier("testservice")
	private TestService testservice;
	
	@GetMapping("/productList")
	public String productList(Model model,
							  Criteria cri) {
		
		
		ArrayList<TestVO> list = testservice.getList(cri);
		
		System.out.println(list.toString());
		
		int total = testservice.getTotal(cri);
		pageVO pageVO = new pageVO(cri, total);
		

		
		
		model.addAttribute("list", list);
		model.addAttribute("pageVO", pageVO);
		
		return "test/productList";
	}
	
	@GetMapping("/productDetail")
	public String productDetail(@RequestParam("test_id") int test_id,
								Model model) {
		
		ArrayList<TestUploadVO>  uploadvo = testservice.getImg(test_id);
		TestVO vo = testservice.getDetail(test_id);
		model.addAttribute("vo",vo);
		model.addAttribute("Imgvo",uploadvo);
		
		return "test/productDetail";
	}
	
	@GetMapping("/productReg")
	public String productReg() {
		
		return "test/productReg";
	}
	
	@PostMapping("/Testregist")
	public String Testregist(TestVO vo,
							 RedirectAttributes RA,
							 @RequestParam("file") List<MultipartFile> list) {
		
		list = list.stream().filter( f  -> f.isEmpty() == false).collect(Collectors.toList());
		
		for(MultipartFile f : list) {
			
			if(f.getContentType().contains("image") == false ) {
				RA.addFlashAttribute("msg","이미지를 삽입하세요");
				return "redirect:/product/productList";
			}
		}
		

		
//		int result = testservice.testregist(vo);
//		
		
		int result = testservice.testregist(vo,list);
		
		if( result != 1) {
			RA.addFlashAttribute("msg","등록에 성공했습니다");

		} else {
			RA.addFlashAttribute("msg","등록에 성공했습니다");
		}
		return "redirect:/test/productList";

	}
	
	
	
}
