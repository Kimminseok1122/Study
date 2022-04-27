package com.coding404.myweb.command;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestUploadVO {

//	CREATE TABLE TEST_UPLOAD(
//			UPLOAD_NO INT PRIMARY KEY AUTO_INCREMENT,
//			FILENAME VARCHAR(100) NOT NULL, #실제 파일명
//		    FILEPATH VARCHAR(100) NOT NULL, #DATE형태의 폴더명
//		    UUID VARCHAR(50) NOT NULL, #파일이름앞에 들어가는 랜덤값
//		    REGDATE TIMESTAMP DEFAULT NOW(),
//		    TEST_ID INT, ##FK
//		    TEST_WRITER VARCHAR(20) ##편의성을 위한 FK
//		);
	
	
	private Integer upload_no;
	private String filename;
	private String filepath;
	private String uuid;
	private LocalDateTime regdate;
	private Integer test_id;
	private String test_writer;
	
}
