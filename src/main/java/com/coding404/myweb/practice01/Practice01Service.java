package com.coding404.myweb.practice01;

import java.util.ArrayList;

import com.coding404.myweb.Prac01Util.Criteria;
import com.coding404.myweb.command.Practice01VO;

public interface Practice01Service {

	public int regist(Practice01VO vo);
	
	public ArrayList<Practice01VO> getlist(Criteria cri);
	
	public Practice01VO getdetail(int prac_number);
	
	public int update(Practice01VO vo);
	
	public int delete(int prac_number);
	
	public ArrayList<Practice01VO> getMelist();
	
	public int getTotal(Criteria cri);
	
}
