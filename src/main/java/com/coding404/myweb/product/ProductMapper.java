package com.coding404.myweb.product;

import java.util.ArrayList;


import org.apache.ibatis.annotations.Mapper;


import com.coding404.myweb.command.CategoryVO;
import com.coding404.myweb.command.ProductUploadVO;
import com.coding404.myweb.command.ProductVO;
import com.coding404.myweb.util.Criteria;

@Mapper//이거 필수
public interface ProductMapper {

	public int regist(ProductVO vo); //등록
	
	public int registFile(ProductUploadVO vo);
	
	public ArrayList<ProductVO> getList(Criteria cri);
	
	public ProductVO getDetail(int prod_id);
	
	public int update(ProductVO vo);
	
	public int delete(int prod_id);
	
	public int getTotal(Criteria cri);
	
	public ArrayList<CategoryVO> getCategory();
	
	public ArrayList<CategoryVO> getCategoryChild(CategoryVO vo);
	
	public ArrayList<ProductUploadVO> getDetailImg(int prod_id);

}
