package com.coding404.myweb.controller;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.coding404.myweb.Prac01Util.Criteria;
import com.coding404.myweb.Prac01Util.pageVO;
import com.coding404.myweb.command.Practice01VO;
import com.coding404.myweb.practice01.Practice01Service;

@Controller
@RequestMapping("/practice01")
public class Practice01Controller {
	
		@Autowired
		@Qualifier("practice01")
		Practice01Service practice01Service;
		
		
		@GetMapping("/topicListAll")
		public void topicList(Model model, Criteria cri) {
			
			
			
			int total = practice01Service.getTotal(cri);
			
			pageVO vo = new pageVO(cri, total);
					
			System.out.println(vo.toString());
			
			
			ArrayList<Practice01VO> list = practice01Service.getlist(cri);
			
			
			
			model.addAttribute("list", list);
			model.addAttribute("pageVO", vo);
		}
		
		@GetMapping("/topicDetail")
		public void topicDetail(@RequestParam("prac_number") int prac_number,
								Model model) {
			
			Practice01VO vo = practice01Service.getdetail(prac_number);
			
			model.addAttribute("vo", vo);
			
		}
		
		@GetMapping("/topicListMe")
		public void topicListMe(Model model, Criteria cri) {
			
			ArrayList<Practice01VO> list = practice01Service.getlist(cri);
			int total = practice01Service.getTotal(cri);
			
			pageVO vo = new pageVO(cri, total);
			
			model.addAttribute("list", list);
			model.addAttribute("pageVO", vo);
		}
		
		@PostMapping("/topicModify")
		public void topicModify(@RequestParam("prac_number") int prac_number,
								Model model) {
			Practice01VO vo = practice01Service.getdetail(prac_number);
			
			model.addAttribute("vo", vo);
		}
		
		@GetMapping("/topicReg")
		public void topicReg() {
			
		}
		
		@PostMapping("/prac_Reg")
		public String prac_Reg(@Valid Practice01VO vo,
							   Errors errors,
							   RedirectAttributes RA,
							   Model model) {
			
			if(errors.hasErrors()) {
				
				List<FieldError> list = errors.getFieldErrors();
				for(FieldError err : list ) {
					
					if(err.isBindingFailure()) {
						model.addAttribute("valid_"+ err.getField(), "형식을 확인해");
					} else {
						model.addAttribute("valid_"+ err.getField(), err.getDefaultMessage());
					}
					
				}
				
				model.addAttribute("vo",vo);
				return "/practice01/topicReg";
			}
			
			
			int result = practice01Service.regist(vo);
			
			if( result == 1 ) {
				RA.addFlashAttribute("msg", "등록에 성공하였습니다");
			} else {
				RA.addFlashAttribute("msg", "등록에 실패했습니다");
			}
			
			return "redirect:/practice01/topicListAll";
		}
		
		@PostMapping("/prac_modify")
		public String prac_modify(@Valid Practice01VO vo,
								  Errors errors,
								  RedirectAttributes RA,
								  Model model) {
			
			if(errors.hasErrors()) {
				
				List<FieldError> list = errors.getFieldErrors();
				for(FieldError err : list) {
					
					if(err.isBindingFailure()) {
						model.addAttribute("valid_" + err.getField() , "형식에 맞게 적어라");
					} else {
						model.addAttribute("valid_" + err.getField() , err.getDefaultMessage());
					}
				}
				
				model.addAttribute("vo", vo);
				return "practice01/topicModify";
			}
			System.out.println(vo.toString());
			int result = practice01Service.update(vo);
			return "redirect:/practice01/topicListAll";
		}
		
		@PostMapping("/prac_delete")
		public String prac_delete(@RequestParam("prac_number") int prac_number,
								  RedirectAttributes RA) {
			System.out.println(prac_number);
			
			int result = practice01Service.delete(prac_number);
			
			if(result == 1) {
				RA.addFlashAttribute("msg" , "삭제를 성공했습니다");
			} else {
				RA.addFlashAttribute("msg" , "삭제를 실패했습니다");
		}
			
			return "redirect:/practice01/topicListAll";
		}
	
}
