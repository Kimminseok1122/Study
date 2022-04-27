package com.coding404.myweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.coding404.myweb.util.interceptor.MenuHandler;
import com.coding404.myweb.util.interceptor.UserAuthHandler;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer{

	@Bean
	public UserAuthHandler userAuthHandler() {
		return new UserAuthHandler();
	}
	
	
	@Bean
	public MenuHandler menuHandler() {
		return new MenuHandler();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
//		registry.addInterceptor(userAuthHandler())
//				.addPathPatterns("/user/*")		//user페이지에 적용
//				.addPathPatterns("/product/**") //product페이지에 적용
//				.addPathPatterns("/main")
//				.excludePathPatterns("/user/login");				
//	}

		registry.addInterceptor( menuHandler() )
				.addPathPatterns("/product/**");
				
	//인터셉터 관련 스프링 설정 파일
	}
	
}
