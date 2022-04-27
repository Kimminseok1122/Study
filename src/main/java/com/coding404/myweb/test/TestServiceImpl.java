package com.coding404.myweb.test;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.coding404.myweb.TestUtil.Criteria;
import com.coding404.myweb.command.TestCategoryVO;
import com.coding404.myweb.command.TestUploadVO;
import com.coding404.myweb.command.TestVO;

@Service("testservice")
public class TestServiceImpl implements TestService{
	
	@Value("${project.upload.path}")
	private String uploadpath;
	
	
	@Autowired
	private TestMapper testmapper;

	@Transactional
	@Override
	public int testregist(TestVO vo, List<MultipartFile> list) {
		
		int result = testmapper.testregist(vo);
		
		for(MultipartFile f : list) {
			
			String originname = f.getOriginalFilename();
			
			String filename = originname.substring( originname.lastIndexOf("\\")+1 );
			
			String filepath = filemaker();
			
			String uuid = UUID.randomUUID().toString();
			
			String savename = uploadpath + "\\" + filepath + "\\" + uuid + "_" + filename;
			
			File file = new File(savename);
			
			try {
				f.transferTo(file);
			} catch (IOException e) {
				e.printStackTrace();
				return 0;
			}
			
						
			TestUploadVO uploadvo = 	TestUploadVO.builder()
											.filename(filename)
											.filepath(filepath)
											.test_writer(vo.getTest_writer())
											.uuid(uuid)
											.build();
			
			testmapper.registFile(uploadvo);
					
			
		}
		return result;
	}
	
	public String filemaker() {
		
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd");
		
		String date = LocalDateTime.now().format(format);
		
		File file = new File(uploadpath + "\\" + date);
		if(file.exists() == false) {
			file.mkdir();
		}
		
		return date;
	}

	@Override
	public ArrayList<TestVO> getList(Criteria cri) {
		return testmapper.getList(cri);
	}

	@Override
	public TestVO getDetail(int test_id) {
		return testmapper.getDetail(test_id);
	}

	@Override
	public int getTotal(Criteria cri) {
		return testmapper.getTotal(cri);
	}

	@Override
	public ArrayList<TestCategoryVO> getCategory() {
		return testmapper.getCategory();
	}

	@Override
	public ArrayList<TestCategoryVO> getCategoryChild(TestCategoryVO vo) {
		return testmapper.getCategoryChild(vo);
	}

	@Override
	public ArrayList<TestUploadVO>  getImg(int test_id) {
		return testmapper.getImg(test_id);
	}

	
	
	
	
}
