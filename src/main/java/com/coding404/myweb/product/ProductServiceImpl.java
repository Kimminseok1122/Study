package com.coding404.myweb.product;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.coding404.myweb.command.CategoryVO;
import com.coding404.myweb.command.ProductUploadVO;
import com.coding404.myweb.command.ProductVO;
import com.coding404.myweb.util.Criteria;

@Service("productSerivce")
public class ProductServiceImpl implements ProductService{
	
	@Value("${project.upload.path}")
	private String uploadpath;
	
	@Autowired
	private ProductMapper productMapper;
	
	
	//multi insert 작업 (상품 insert -> 파일 업로드 -> 업로드테이블 insert)
	//둘중 하나라도 insert 안된다면 롤백이 일어남
	@Transactional(rollbackFor = Exception.class )
	@Override
	public int regist(ProductVO vo, List<MultipartFile> list) {
		
		int result = productMapper.regist(vo);
		
		//1. 파일명의 추출
		for(MultipartFile f : list) {
			String originName = f.getOriginalFilename();
			
			String filename = originName.substring( originName.lastIndexOf("\\")+ 1 );
			//2. 업로드 된 파일을 폴더별로 저장(파일생성)
			String filepath = makeFolder();
			//3. 랜덤값 생성
			String uuid = UUID.randomUUID().toString();
			
			//4. 최종 경로
			String savename = uploadpath + "\\" + filepath + "\\" + uuid + "_" + filename;
			
			//5. 업로드 진행
			try {
				f.transferTo( new File(savename) );
			}  catch (IOException e) {
				e.printStackTrace();
				return 0;
			}
	
	  //prod_id 는 화면에서 전달되지 않기 떄문에 현재 사용할 수 없음
	  //mybatis에 selectKey 기능을 사용. insert전 후에 특정 테이블의 키를 구하는 기능
	  ProductUploadVO prod = ProductUploadVO.builder()
						   .filename(filename)
						   .filepath(filepath)
						   .uuid(uuid)
						   .prod_writer( vo.getProd_writer())
						   .build();
						  
			
	  	 productMapper.registFile(prod);
		}
		//insert
		
		return result;
	}
	
	public String makeFolder() {
		
		//날짜별로 폴더생성
		DateTimeFormatter datatime = DateTimeFormatter.ofPattern( "yyMMdd" );
		
		String date = LocalDateTime.now().format(datatime);
		
		File file = new File(uploadpath+ "\\"+ date); //java.io
		if(file.exists() == false ) { //폴더가 존재하면 true,존재하지 않으면 false
			file.mkdir(); //폴더가 생성			
		}
		
		return date;  //년월일 리턴
	}

	@Override
	public ArrayList<ProductVO> getList(Criteria cri) {
		return productMapper.getList(cri);
	}

	@Override
	public ProductVO getDetail(int prod_id) {
		return productMapper.getDetail(prod_id);
	}

	@Override
	public int update(ProductVO vo) {
		return productMapper.update(vo);
	}

	@Override
	public int delete(int prod_id) {
		return productMapper.delete(prod_id);
	}

	@Override
	public int getTotal(Criteria cri) {
		return productMapper.getTotal(cri);
	}

	@Override
	public ArrayList<CategoryVO> getCategory() {
		return productMapper.getCategory();
	}

	@Override
	public ArrayList<CategoryVO> getCategoryChild(CategoryVO vo) {
		return productMapper.getCategoryChild(vo);
	}

	@Override
	public ArrayList<ProductUploadVO> getDetailImg(int prod_id) {
		return productMapper.getDetailImg(prod_id);
	}
	
	

	
}
