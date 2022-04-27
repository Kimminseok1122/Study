package com.coding404.myweb.test;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.coding404.myweb.TestUtil.Criteria;
import com.coding404.myweb.command.TestCategoryVO;
import com.coding404.myweb.command.TestUploadVO;
import com.coding404.myweb.command.TestVO;

@Mapper
public interface TestMapper {

	public int testregist(TestVO vo);
	
	public ArrayList<TestVO> getList(Criteria cri);
	
	public TestVO getDetail(int test_id);
	
	public int registFile(TestUploadVO vo);
	
	
	public int getTotal(Criteria cri);
	
	public ArrayList<TestCategoryVO> getCategory();
	
	public ArrayList<TestCategoryVO> getCategoryChild(TestCategoryVO vo);
	
	public ArrayList<TestUploadVO>  getImg(int test_id);
}
