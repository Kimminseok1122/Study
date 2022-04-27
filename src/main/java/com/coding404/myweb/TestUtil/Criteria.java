package com.coding404.myweb.TestUtil;

import lombok.Data;

@Data
public class Criteria {
	
	private int pageNum;
	private int amount;
	
	private String searchname;
	private String searchcontent;
	private String order;
	
	public Criteria() {
		this.pageNum = 1;
		this.amount = 10;
	}

	public Criteria(int pageNum, int amount) {
		super();
		this.pageNum = pageNum;
		this.amount = amount;
	}
	

	
}
