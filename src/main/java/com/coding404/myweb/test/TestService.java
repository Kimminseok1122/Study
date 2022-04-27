package com.coding404.myweb.test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.coding404.myweb.TestUtil.Criteria;
import com.coding404.myweb.command.TestCategoryVO;
import com.coding404.myweb.command.TestUploadVO;
import com.coding404.myweb.command.TestVO;

public interface TestService {
	
	public int testregist(TestVO vo,List<MultipartFile> list);
	
	public ArrayList<TestVO> getList(Criteria cri);
	
	public TestVO getDetail(int test_id);
	
	public int getTotal(Criteria cri);
	
	public ArrayList<TestCategoryVO> getCategory();
	
	public ArrayList<TestCategoryVO> getCategoryChild(TestCategoryVO vo);
	
	public ArrayList<TestUploadVO> getImg(int test_id);
}
