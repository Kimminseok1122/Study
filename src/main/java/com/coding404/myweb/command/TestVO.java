package com.coding404.myweb.command;

import java.time.LocalDateTime;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestVO {

	private Integer test_id;
	private LocalDateTime test_regdate;
	private String category_key;
	private String category_nav;
	
	
	@NotBlank
	@Pattern(regexp =  "[0-9]{4}-[0-9]{2}-[0-9]{2}", message = "날짜 표현형식을 지켜주세요")
	private String  test_enddate;
	
	@NotNull(message = "작성자를 적어주세요")
	private String test_writer;
	
	@NotNull(message = "가격을 적어주세요")
	private Integer test_price;
	
	@Min(value = 0, message = "수량은 0개 이상입니다")
	@NotNull(message = "수량을 적어주세요")
	private Integer test_count;
	
	@Min(value = 0, message = "할인율은 0% 이상입니다")
	private Integer test_discount;
	
	private String test_prod_purchase_yn;
	
	@NotEmpty(message = "설명을 적어주세요")
	private String  test_content;
	
	private String  test_comment;
	
	@NotEmpty(message = "상품명을 적어주세요")
	private String test_name;
	
}
