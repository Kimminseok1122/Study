package com.coding404.myweb.practice01;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coding404.myweb.Prac01Util.Criteria;
import com.coding404.myweb.command.Practice01VO;

@Service("practice01")
public class Practice01ServiceImpl implements Practice01Service{

	@Autowired
	Practice01Mapper practice01Mapper;
	
	@Override
	public int regist(Practice01VO vo) {
		return practice01Mapper.regist(vo);
	}

	@Override
	public ArrayList<Practice01VO> getlist(Criteria cri) {
		return practice01Mapper.getlist(cri);
	}

	@Override
	public Practice01VO getdetail(int prac_number) {
		return practice01Mapper.getdetail(prac_number);
	}

	@Override
	public int update(Practice01VO vo) {
		return practice01Mapper.update(vo);
	}

	@Override
	public int delete(int prac_number) {
		return practice01Mapper.delete(prac_number);
	}

	@Override
	public ArrayList<Practice01VO> getMelist() {
		return practice01Mapper.getMelist();
	}

	@Override
	public int getTotal(Criteria cri) {
		// TODO Auto-generated method stub
		return practice01Mapper.getTotal(cri);
	}
	
	

	
	
	
	
	
	

	
}
