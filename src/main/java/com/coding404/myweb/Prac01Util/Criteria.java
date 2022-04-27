package com.coding404.myweb.Prac01Util;

import lombok.Data;

@Data
public class Criteria {
	
	private	int page;
	private int amount ;
	
	private String searchName;
	private String searchTitle;
	
	public Criteria() {
		this.page = 1;
		this.amount = 10;
	}

	public Criteria(int page, int amount) {
		super();
		this.page = page;
		this.amount = amount;
	}
	
	
	
}
