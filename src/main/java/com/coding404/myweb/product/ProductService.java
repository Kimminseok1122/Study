package com.coding404.myweb.product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.coding404.myweb.command.CategoryVO;
import com.coding404.myweb.command.ProductUploadVO;
import com.coding404.myweb.command.ProductVO;
import com.coding404.myweb.util.Criteria;

public interface ProductService {
	
	public int regist(ProductVO vo, List<MultipartFile> list); //등록
	
	public ArrayList<ProductVO> getList(Criteria cri);
	
	public ProductVO getDetail(int prod_id);
	
	public int update(ProductVO vo);
	
	public int delete(int prod_id);
	
	public int getTotal(Criteria cri);
	
	public ArrayList<CategoryVO> getCategory();
	
	public ArrayList<CategoryVO> getCategoryChild(CategoryVO vo);
	
	public ArrayList<ProductUploadVO> getDetailImg(int prod_id);
}
