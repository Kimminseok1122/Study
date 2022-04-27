package com.coding404.myweb.TestUtil;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.Data;

@Data
public class pageVO {

	private int start;
	private int end;
	private int realEnd;
	private boolean next;
	private boolean prev;
	
	private int pagenum;
	private int amount;
	
	private int total;
	private Criteria cri;
	private List<Integer> pageList;
	
	
	public pageVO(Criteria cri , int total) {
		this.pagenum = cri.getPageNum();
		this.amount = cri.getAmount();
		this.total = total;
		this.cri = cri;
		
		this.end = (int)Math.ceil(this.pagenum/10.0)*10;
		this.start = this.end - 10 + 1 ;
		
		this.realEnd = (int)Math.ceil((this.total/(double)this.amount));
		
		if(this.end > this.realEnd) {
			this.end = this.realEnd;
		}
		this.next = this.realEnd > this.end;
		this.prev = this.start > 1 ;
		
		this.pageList = IntStream.rangeClosed(start, end ).boxed().collect(Collectors.toList());
		
	}
	
	
	
	
}
