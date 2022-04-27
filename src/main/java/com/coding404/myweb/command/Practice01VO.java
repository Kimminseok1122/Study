package com.coding404.myweb.command;



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
public class Practice01VO {
	
	private Integer prac_number;
	
	@NotBlank(message = "공백으로 넣을수 없습니다")
	@Pattern(regexp = "[0-9]{4}-[0-9]{2}-[0-9]{2}", message = "형식에 맞춰서 입력해주세요")
	private String prac_regdate;
	
	@NotBlank(message = "띄어쓰기 ㄴㄴ")
	private String prac_name;
	
	@NotEmpty(message = "제목지어라")
	private String prac_title;
	
	@NotEmpty(message = "내용 채워라")
	private String prac_content;
}
