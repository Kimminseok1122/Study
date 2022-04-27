package com.coding404.myweb.Prac01Util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.Data;

@Data 	
public class pageVO {
	
	private int start;
	private int end;
	
	private int page;
	private int amount;
	private int realEnd;
	
	private boolean next;
	private boolean prev;
	
	private int total;
	
	private Criteria cri; 
	private List<Integer> pageList;
	
	// 1. end
	
	public pageVO(Criteria cri, int total) {
		
		this.page = cri.getPage();
		this.amount = cri.getAmount();
		this.cri = cri;
		this.total = total;
		
		
		// 1. end
		
		this.end = (int)Math.ceil( this.page / 10.0 ) * 10;
		
		// 2. start
		
		this.start = this.end - 10 + 1 ;
		
		// 3 . realEnd
		
		this.realEnd = (int)Math.ceil( this.total / (double)this.amount);
		
		// 4. realend 결정
		
		if( this.realEnd < this.end) {
			this.end = this.realEnd;
		}
		
		// 5. pageList 결정
		
		this.pageList = IntStream.rangeClosed(this.start, this.end).boxed().collect(Collectors.toList());
		
		// 6 . prev
		
		this.prev =  this.start > 1;
		
		// 7 . next
		
		this.next = this.realEnd > this.end;
		
		
	}
	
	
}
