package com.coding404.myweb.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.coding404.myweb.command.ProductUploadVO;
import com.coding404.myweb.command.ProductVO;
import com.coding404.myweb.product.ProductService;
import com.coding404.myweb.util.Criteria;
import com.coding404.myweb.util.PageVO;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	@Qualifier("productSerivce")
	private ProductService productService;
	
	//등록화면
	@GetMapping("/productReg")
	public String productReg() {
		
		return "product/productReg";
	}
	
	//목록화면
	@GetMapping("/productList")
	public String productList(Model model, Criteria cri) {
		
		//데이터 저장
		//ArrayList<ProductVO> list = productService.getList();
		
		
		
		//페이지
		ArrayList<ProductVO> list = productService.getList(cri);
		int total = productService.getTotal(cri);
		PageVO pageVO = new PageVO(cri, total);
		
		System.out.println(list.toString());
		
		model.addAttribute("list", list);
		model.addAttribute("pageVO", pageVO);
		
		return "product/productList";
	}
	
	/*
	 * 이미지 데이터 처리방법
	 * 1. 업로드 테이블의 이미지를 select
	 * 2. 화면에 반복문으로 img태그의 src속성에 파일값 처리
	 * 3. restController에 이미지 정보를 반환하는 메서드 생성
	 * 
	 * */
	
	//상세화면
	@GetMapping("/productDetail")
	public String productDetail(@RequestParam("prod_id") int prod_id,
								Model model) {
		
		//데이터 저장
		ProductVO prodVO = productService.getDetail(prod_id);
		model.addAttribute("prodVO", prodVO);
		
		//이미지 데이터를 셀렉팅
		ArrayList<ProductUploadVO> list = productService.getDetailImg(prod_id);
		model.addAttribute("prodImg",list);
		
		return "product/productDetail";
	}
	
	//상품등록 폼
	@PostMapping("/productForm")
	public String productForm(ProductVO vo,
							  RedirectAttributes RA,
							  @RequestParam("file") List<MultipartFile> list
							  ) {
		
		//파일 확인(form형식을 multipart타입으로 반드시 선언)
//		for(MultipartFile f : list) {
//			System.out.println(f.isEmpty());
//			System.out.println(f.getContentType());
//		}
		
		//1. 빈형태로 넘어오는 이미지는 제거
		list = list.stream().filter( f -> f.isEmpty() == false).collect(Collectors.toList());
		
		//2. 업로드된 확장자가 이미지만 가능하도록 처리
		for(MultipartFile f : list) {
			if(f.getContentType().contains("image") == false ) { //image
				RA.addFlashAttribute("msg","이미지를 삽입하세요");
				return "redirect:/product/productList";
			}
		}
		
		//3. 파일업로드 코드는 서비스영역으로 위임
		//vo를 등록
		//int result = productService.regist(vo);
		int result = productService.regist(vo, list);
		
		if(result == 1) {
			RA.addFlashAttribute("msg", vo.getProd_name() + "이 정상 등록 되었습니다.");
		} else {
			RA.addFlashAttribute("msg", vo.getProd_name() + "등록실패, 관리자에게 문의하세요");
		}
		
		return "redirect:/product/productList";//목록화면으로
	}
	
	@PostMapping("/prodUpdate")
	public String prodUpdate(@Valid ProductVO vo,
							 Errors errors,
							 RedirectAttributes RA,
							 Model model) {
		
		if(errors.hasErrors()) {
			
			List<FieldError> list = errors.getFieldErrors();
			
			for(FieldError err : list) {
//				System.out.println(err.getField());
//				System.out.println(err.getDefaultMessage());// 잘 동작하는지 확인
				if(err.isBindingFailure()) {
					model.addAttribute("valid_" + err.getField(), "형식을 확인하세요");
				} else {
					model.addAttribute("valid_" + err.getField(), err.getDefaultMessage());
				}
				
			}
			
			//화면에서는 prodVO를 쓰고있기때문에 같은이름으로 보내서 처리
			model.addAttribute("prodVO", vo);
			return "product/productDetail";
		}
		
		
		
		//System.out.println(vo.toString()); 자기가 원하는값이 다 mapping되서 오는지 꼭 확인해볼것
		int result = productService.update(vo);
		
		if(result == 1) {
			RA.addFlashAttribute("msg", vo.getProd_name() + "이 수정되었습니다");
		} else {
			RA.addFlashAttribute("msg", "수정에 실패했습니다. 관리자에게 문의하세요");
		}
		
		return "redirect:/product/productList";
	}
	
	
	@PostMapping("/prodDelete")
	public String prodDelete(@RequestParam("prod_id") int prod_id,
							 RedirectAttributes RA) {
		
		int result = productService.delete(prod_id);
		
		if(result == 1) {
			RA.addFlashAttribute("msg", "상품이 삭제되었습니다");
			
		} else {
			RA.addFlashAttribute("msg", "삭제에 실패했습니다");
		}
		
		return "redirect:/product/productList";
		
	}
	
	
	
	
	
	
	
	
	
}
