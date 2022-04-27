package com.coding404.myweb.command;


import java.time.LocalDateTime;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductVO {
	
	private Integer prod_id;
	private LocalDateTime prod_regdate;
	
	@NotBlank(message = "판매종료일은 필수입니다")
	@Pattern(regexp = "[0-9]{4}-[0-9]{2}-[0-9]{2}", message = "yyyy년-mm월-dd일 형식입니다")
	private String prod_enddate;
	
	private String prod_category;
	private String category_nav;
	
	@NotBlank(message = "작성자는 필수입니다")
	private String prod_writer;
	
	@NotBlank(message = "상품명은 필수입니다")
	private String prod_name;
	
	@Min(value = 0, message = "가격은 0원 이상입니다")//최소값을 의미함
	@NotNull(message = "가격은 필수 입니다")
	private Integer prod_price;
	
	@Min(value = 0, message = "수량은 0개 이상입니다")//최소값을 의미함
	@NotNull(message = "수량은 필수 입니다")
	private Integer prod_count;
	
	@Min(value = 0, message = "할인율은 0% 이상입니다")
	@NotNull(message = "할인율은 필수 입니다")
	private Integer prod_discount;
	
	
	private String prod_purchase_yn;
	
	@NotBlank(message = "상품설명은 필수 입니다")
	private String prod_content;
	
	private String prod_comment;
	
}
